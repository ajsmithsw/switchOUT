package com.ajsmithsw.switchout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.com.ajsmithsw.switchout.inappbilling.util.IabHelper;
import com.com.ajsmithsw.switchout.inappbilling.util.IabResult;
import com.com.ajsmithsw.switchout.inappbilling.util.Inventory;
import com.com.ajsmithsw.switchout.inappbilling.util.Purchase;
import com.crashlytics.android.Crashlytics;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;


import io.fabric.sdk.android.Fabric;

/**
 * Created by Alexander Smith on 23/05/15.
 */
public class StartActivity extends Activity {

    private int facebookRedeemed, twitterRedeemed, hintsRemaining, bonus;

    // TODO Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "L2GfBsvHncyrrPNnl3hQJG74Z";
    private static final String TWITTER_SECRET = "d7LeIbX7kQLfGjAjIFz8FRXRQc2YNzA80WQSGJ6bvUvCCowyWr";
    private static final int TWEET_COMPOSER_REQUEST_CODE = 100;

    private Context myContext;

    //Home screen buttons
    ImageView playBtn, levelBtn, clearBtn, fbShare, twShare, buyHintsBtn;
    TextView smText;

    //social media
    CallbackManager callbackManager;
    ShareDialog shareDialog;


    //in-app billing
    private static final String TAG = "In-app Billing";
    IabHelper mHelper;
    static final String ITEM_SKU = "100_hints";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lookupData();

        bonus = 15;

        //Set up Twitter
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new Crashlytics());
        //

        //Set up Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        //Set the layout
        myContext = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_layout);

        //remove social media text if both have been redeemed
        smText = (TextView) findViewById(R.id.sm_text);
        if (facebookRedeemed == 1 && twitterRedeemed == 1) {
            smText.setText("");
        }

        //Set up in-app billing
        String base64EncodedPublicKey = getString(R.string.base64_full);
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(false);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                public void onIabSetupFinished(IabResult result) {
                     if (!result.isSuccess()) {
                         Log.d(TAG, "In-app Billing setup failed: " +
                            result);
                     }

                    if (mHelper == null) return;

                    Log.d(TAG, "In-app Billing is set up OK, querying inventory...");

                    mHelper.queryInventoryAsync(mGotInventoryListener);

                }
        });


        //Ad banner

        AdView mAdView = (AdView) findViewById(R.id.adViewTwo);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("B010CB7FBEAB9CE5AC9E14B88B13B50F")
                .build();
        mAdView.loadAd(adRequest);


        playBtn = (ImageView) findViewById(R.id.playbutton);
        levelBtn = (ImageView) findViewById(R.id.levelselectbutton);
        clearBtn = (ImageView) findViewById(R.id.settingsbutton);

        fbShare = (ImageView) findViewById(R.id.facebookbutton);
        twShare = (ImageView) findViewById(R.id.twitterbutton);

        if (facebookRedeemed == 0) {
            fbShare.setImageResource(R.drawable.fbunticked);
        } else {
            fbShare.setImageResource(R.drawable.home_facebookredeemed);
        }

        if (twitterRedeemed == 0) {
            twShare.setImageResource(R.drawable.twunticked);
        } else {
            twShare.setImageResource(R.drawable.home_twitterredeemed);
        }

        buyHintsBtn = (ImageView) findViewById(R.id.buybutton);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playBtn.setImageResource(R.drawable.playclicked);

                /*
                determine how many levels have been completed, then set the level to load
                to be that number + 1 (i.e. the next unsolved level)
                 */
                sqlDBHelper dbh = new sqlDBHelper(myContext);
                SQLiteDatabase SQ = dbh.getReadableDatabase();
                long numEntries = DatabaseUtils.queryNumEntries(SQ, sqlTableData.TableInfo.TABLE_NAME);

                //TODO - all ok?
                if (numEntries < 80) {
                    int levelToLoad = (int) numEntries + 1;
                    LoadLevel.setLevelToLoad(levelToLoad);

                    Intent intent = new Intent(myContext, MainActivity.class);
                    myContext.startActivity(intent);
                    SQ.close();
                } else if (numEntries == 80) {
                    int levelToLoad = (int) numEntries;
                    LoadLevel.setLevelToLoad(levelToLoad);

                    Intent intent = new Intent(myContext, MainActivity.class);
                    myContext.startActivity(intent);
                    SQ.close();
                } else {
                    //load the level select activity
                    Intent intent = new Intent(myContext, LevelSelector.class);
                    myContext.startActivity(intent);
                    SQ.close();
                }

            }
        });

        levelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levelBtn.setImageResource(R.drawable.levelclicked);

                Intent intent = new Intent(myContext, LevelSelector.class);
                myContext.startActivity(intent);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearBtn.setImageResource(R.drawable.settingsclicked);

                Intent intent = new Intent(myContext, SettingsPage.class);
                myContext.startActivity(intent);

            }
        });

        //operate Facebook button
        fbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (facebookRedeemed == 0) {
                    fbShare.setImageResource(R.drawable.fbunticked_clicked);
                } else {
                    fbShare.setImageResource(R.drawable.fbclicked);
                }

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("switchOUT for Android")
                            .setContentDescription(
                                    "I'm playing switchOUT for Android, the best lights out game on the Google Play Store!")
                            .setContentUrl(Uri.parse("http://play.google.com/store/apps/details?id=com.ajsmithsw.switchout"))
                            .build();

                    shareDialog.show(linkContent);
                }
            }
        });

        //operate Twitter button
        twShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (twitterRedeemed == 0) {
                    twShare.setImageResource(R.drawable.twunticked_clicked);
                } else {
                    twShare.setImageResource(R.drawable.twclicked);
                }


                Intent intent = new TweetComposer.Builder(myContext)
                        .text("I'm playing switchOUT for Android, check it out! http://bit.ly/1fjWmg1")
                        .createIntent();
                startActivityForResult(intent, TWEET_COMPOSER_REQUEST_CODE);
            }
        });

        buyHintsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buyHintsBtn.setImageResource(R.drawable.hintsclicked);

                Log.d("buy hints button", "Buy Hints button clicked, initiate purchase");
                beginPurchase();
            }
        });



    }



    IabHelper.QueryInventoryFinishedListener mGotInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (mHelper == null) return;

            if (result.isFailure()) {
                Log.d("mGotInventoryListener", "isFailure");
            }
            else {
                Log.d("mGotInventoryListener", "Finished");
            }

            Purchase hintsPurchase = inventory.getPurchase(ITEM_SKU);

            if (hintsPurchase != null && verifyDeveloperPayload(hintsPurchase)) {
                Log.d(TAG, "There's a purchase waiting in account. Consuming...");

                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU), mConsumeFinishedListener);
                return;
            }
        }
    };


    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {

            if (result.isFailure()) {
                // Handle error
                Log.d("PFinishedListener", "isFailure");
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                Log.d("PFinishedListener", "purchase finished, call consumeItem()");
                consumeItem();
            }

        }
    };

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {


            if (result.isFailure()) {
                // Handle failure
                Log.d("RInventoryListener", "isFailure");

            } else {
                Log.d("RInventoryListener", "success, mHelper.consumeAsync...");
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                        mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {

                        Log.d("item consumed", "time to credit the extra hints!");

                        int hRm = 0;
                        sqlDBHelper dbh = new sqlDBHelper(myContext);
                        SQLiteDatabase SQ = dbh.getReadableDatabase();
                        Cursor c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.HINT_TABLE_NAME
                                + " WHERE " + sqlTableData.TableInfo.PLAYER + "='1'", null);
                        if (c.moveToFirst()) {
                            hRm = c.getInt(1);
                        }
                        SQ = dbh.getWritableDatabase();
                        try {
                            SQ.execSQL("UPDATE " + sqlTableData.TableInfo.HINT_TABLE_NAME + " SET "
                                    + sqlTableData.TableInfo.HINTS_REMAINING + "='" + Integer.toString(hRm + 100)
                                    + "' WHERE " + sqlTableData.TableInfo.PLAYER + "='1'");

                            Log.d("SQL command", "100 hints added successfully");
                            Toast.makeText(myContext, "100 hints added, enjoy!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.d("SQL command", "failed to add hints purchase :( ");
                        }

                        SQ.close();

                    } else {
                        // handle error
                        Log.d("CFinishedListener", "purchase not consumed, uh oh.");
                    }


                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.d("onActivityResult", "requestCode: " + requestCode + ", resultCode: " +
                resultCode + ", data:" + data);

        //check data from both database tables:
        sqlDBHelper dbh = new sqlDBHelper(myContext);
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        Cursor c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.HINT_TABLE_NAME
                + " WHERE " + sqlTableData.TableInfo.PLAYER + "='1'", null);
        if (c.moveToFirst()) {
            hintsRemaining = c.getInt(1);
        }
        Log.d("START Hints remaining:", Integer.toString(hintsRemaining));
        c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.BONUS_TABLE_NAME
                + " WHERE " + sqlTableData.TableInfo.PLAYER_BONUS + "='1'", null);
        if (c.moveToFirst()) {
            facebookRedeemed = c.getInt(1);
            twitterRedeemed = c.getInt(2);
        }
        Log.d("onActivityResult", "facebook redeemed value: " + facebookRedeemed + ", twitter redeemed value: " + twitterRedeemed);


        if (requestCode == TWEET_COMPOSER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("Twitter", "my tweet was sent, has bonus been redeemed?");
                if (twitterRedeemed == 0) {
                    Log.d("Twitter", "twitterRedeemed == 0, so add 15 to hints remaining table and set twitterRedeemed to 1");
                    SQ = dbh.getWritableDatabase();
                    try {
                        SQ.execSQL("UPDATE " + sqlTableData.TableInfo.HINT_TABLE_NAME + " SET "
                                + sqlTableData.TableInfo.HINTS_REMAINING + "='" + Integer.toString(hintsRemaining + bonus)
                                + "' WHERE " + sqlTableData.TableInfo.PLAYER + "='1'");

                        Log.d("SQL command", "15 bonus added successfully");
                        Toast.makeText(myContext, "15 bonus hints added!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("SQL command", "15 bonus not added");
                    }

                    twitterRedeemed = 1;
                    Log.d("twitterRedeemed", "temporary variable set to 1");
                    try {
                        SQ.execSQL("UPDATE " + sqlTableData.TableInfo.BONUS_TABLE_NAME + " SET "
                                + sqlTableData.TableInfo.TWITTER_BONUS + "='1' WHERE "
                                + sqlTableData.TableInfo.PLAYER_BONUS + "='1'");
                        Log.d("SQL Command", "twitter bonus set to 1");
                    } catch (Exception e) {
                        Log.d("SQL command", "bonus table not updated");
                    }



                }


            } else {
                Log.d("Twitter", "tweet was NOT sent");
            }
        } else if (requestCode == 64207) {
            if (resultCode == RESULT_OK) {
                Log.d("Facebook", "Facebook post complete");
                if (facebookRedeemed == 0) {
                    Log.d("Facebook", "facebookRedeemed == 0, so add 15 to hints remaining table and set facebookRedeemed to 1");
                    try {
                        SQ.execSQL("UPDATE " + sqlTableData.TableInfo.HINT_TABLE_NAME + " SET "
                                + sqlTableData.TableInfo.HINTS_REMAINING + "='" + Integer.toString(hintsRemaining + bonus)
                                + "' WHERE " + sqlTableData.TableInfo.PLAYER + "='1'");

                        Log.d("SQL command", "15 bonus added successfully");
                        Toast.makeText(myContext, "15 bonus hints added!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("SQL command", "15 bonus not added");
                    }

                    facebookRedeemed = 1;
                    Log.d("facebookRedeemed", "temporary variable set to 1");

                    try {
                        SQ.execSQL("UPDATE " + sqlTableData.TableInfo.BONUS_TABLE_NAME + " SET "
                                + sqlTableData.TableInfo.FACEBOOK_BONUS + "='1' WHERE "
                                + sqlTableData.TableInfo.PLAYER_BONUS + "='1'");
                    } catch (Exception e) {
                        Log.d("SQL command", "bonus table not updated");
                    }
                } else {
                    Log.d("Facebook", "Facebook bonus already redeemed");
                }
            }

        }

        //check hints remaining once finished
        SQ = dbh.getReadableDatabase();
        c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.HINT_TABLE_NAME
                + " WHERE " + sqlTableData.TableInfo.PLAYER + "='1'", null);
        if (c.moveToFirst()) {
            hintsRemaining = c.getInt(1);
        }
        Log.d("FINAL Hints remaining:", Integer.toString(hintsRemaining));
        SQ.close();

    }


    public void beginPurchase() {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
    }

    /** Verifies the developer payload of a purchase. */
    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */
            return true;
    }

    private void lookupData() {
        //Create database if not exists, &
        //Determine if bonuses have been redeemed
        sqlDBHelper dbh = new sqlDBHelper(this);
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        Cursor c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.BONUS_TABLE_NAME
                + " WHERE " + sqlTableData.TableInfo.PLAYER_BONUS + "='1'", null);
        if (c.moveToFirst()) {
            facebookRedeemed = c.getInt(1);
            twitterRedeemed = c.getInt(2);
        }

        Log.d("bonuses been redeemed?", "Facebook: " + Integer.toString(facebookRedeemed)+
                ", Twitter: " + Integer.toString(twitterRedeemed));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this); //fb

        //reset button images to their un-clicked versions
        playBtn.setImageResource(R.drawable.home_play);
        levelBtn.setImageResource(R.drawable.home_levelselect);
        clearBtn.setImageResource(R.drawable.home_settings);
        buyHintsBtn.setImageResource(R.drawable.home_buyhints);

        lookupData();

        if (facebookRedeemed == 0) {
            fbShare.setImageResource(R.drawable.fbunticked);
        } else {
            fbShare.setImageResource(R.drawable.home_facebookredeemed);
        }

        if (twitterRedeemed == 0) {
            twShare.setImageResource(R.drawable.twunticked);
        } else {
            twShare.setImageResource(R.drawable.home_twitterredeemed);
        }

        if (facebookRedeemed == 1 && twitterRedeemed == 1) {
            smText.setText("");
        } else {
            smText.setText(R.string.redeem_bonuses_text);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppEventsLogger.deactivateApp(this); //fb

        //in-app billing
        if (mHelper != null) mHelper.dispose();
        mHelper = null;

    }

}
