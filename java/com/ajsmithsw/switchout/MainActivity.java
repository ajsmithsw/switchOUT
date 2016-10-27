package com.ajsmithsw.switchout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.com.ajsmithsw.switchout.inappbilling.util.IabHelper;
import com.com.ajsmithsw.switchout.inappbilling.util.IabResult;
import com.com.ajsmithsw.switchout.inappbilling.util.Inventory;
import com.com.ajsmithsw.switchout.inappbilling.util.Purchase;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jirbo.adcolony.AdColony;


public class MainActivity extends Activity {

    private Context myContext;

    private View gameView;

    //in-app billing
    private static final String TAG = "In-app Billing";
    IabHelper mHelper;
    static final String ITEM_SKU = "<ITEM_SKU>";

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameBoard gBoard = new GameBoard(this, null);
        gBoard.setKeepScreenOn(true); //stops the screen going dark and timing out
        //gBoard.setBackgroundColor(Color.BLUE);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Hides the app title
        getWindow().setFlags
                (WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN); //Sets the window to full screen
        //gBoard.setBackground(bgGrad); //set the background to the bgGrad gradient
        setContentView(R.layout.game_view);


        gameView = findViewById(R.id.lightsview);

        AdView mAdView = (AdView) findViewById(R.id.adViewTwo);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("<TEST_DEVICE_ID>").build();
        mAdView.loadAd(adRequest);



        myContext = this;

        //Set up in-app billing
        String base64EncodedPublicKey = getString(R.string.base64_full);
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.d(TAG, "In-app Billing setup failed: " +
                            result);
                } else {
                    Log.d(TAG, "In-app Billing is set up OK");
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                }
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
                Log.d("PurFinishedListener", "isFailure");
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                Log.d("PurFinishedListener", "purchase finished, call consumeItem()");
                //stackOverflow();
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
                Log.d("ReceivedInvListener", "isFailure");

            } else {
                Log.d("ReceivedInvListener", "success, mHelper.consumeAsync...");
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

                            int temp;
                            SQ = dbh.getReadableDatabase();
                            c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.HINT_TABLE_NAME + " WHERE " +
                                    sqlTableData.TableInfo.PLAYER + "='1'", null);
                            if (c.moveToFirst()) {
                                temp = c.getInt(1);
                            } else {
                                temp = 0;
                            }


                            gameView.setTag(temp);
                            gameView.invalidate();


                            Log.d("SQL command", "100 hints added successfully");
                            Toast.makeText(myContext, "100 hints added, enjoy!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.d("SQL command", "failed to add hints purchase :( ");
                        }

                        SQ.close();


                    } else {

                        // handle error
                        Log.d("ConsumeFinishedListener", "purchase not consumed, uh oh.");

                    }


                }
            };

    public void beginPurchase() {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
        Log.d(TAG, "beginPurchase called");
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        AdColony.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdColony.resume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
