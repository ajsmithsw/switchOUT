package com.ajsmithsw.switchout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jirbo.adcolony.AdColonyAdapter;
import com.jirbo.adcolony.AdColonyBundleBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alexander Smith on 18/05/15.
 */
public class GameBoard extends View {

    //VARIABLES

    InterstitialAd mInterstitialAd;

    private Context myContext;
    private float scale;

    //dimensions
    public int screenW, screenH;
    public int lightsW, lightsH;
    public int titleW, titleH;

    //images
    private Bitmap lightOnBmp;
    private Bitmap lightOffBmp;
    private Bitmap homeIcon;
    private Bitmap lightHintActive;
    private Bitmap hintButton, resetButton;
    private Bitmap hintGreen, hintGrey;


    //The main lights array
    public static List<Light> lights;
    public int[] solutionArray;                       // for finding the next hint.

    //variables for the touch gestures
    public int tX;
    public int tY;
    public int touchedId;

    //Game state information
    private int lightsRemaining;
    public static int movesMade;
    public static int currentLevel;
    private int par;

    private boolean nextLevel;
    public int openingLevel;

    //Player stats information
    public int playerBestScore;
    public int playerBestStars;

    //Paint elements
    private Paint whiteTextPaint;
    private Paint lowerGameBanner, bestScoreBanner;

    public int hintsRemaining;


    public Drawable topBannerBg;
    public Typeface pixelFont;
    public Paint pixelFontPaint, smallPixelPaint, smallPixelPaintLeft, smallRedPixelPaint, hintTextPaint;

    public String parTarget;



    //Vectors for solution algorithms

    // The hint matrix was computed in Maple; multiplying it by
    // a vector representing the current board gives a vector
    // representing the moves necessary to produce the current
    // board; since everything is done mod 2, this also gives the
    // moves necessary to solve the game.
    int[][] hint_matrix = {
            { 0,1,1,1,0,0,0,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0 },
            { 1,1,0,1,1,0,1,0,0,0,0,0,1,1,1,0,0,0,1,0,0,0,0 },
            { 1,0,1,1,1,1,0,1,1,0,0,0,1,1,0,1,1,1,1,1,0,1,0 },
            { 1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1 },
            { 0,1,1,0,1,1,0,0,0,0,1,0,1,0,1,0,0,1,0,1,1,1,0 },
            { 0,0,1,0,1,0,1,1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0 },
            { 0,1,0,1,0,1,1,0,1,1,0,0,0,1,0,1,1,1,0,0,0,1,0 },
            { 1,0,1,0,0,1,0,1,1,0,0,0,0,0,1,1,0,1,0,1,1,0,1 },
            { 0,0,1,0,0,0,1,1,1,0,1,0,0,1,1,1,0,0,1,0,0,1,1 },
            { 1,0,0,0,0,1,1,0,0,0,1,0,1,0,1,0,1,1,0,1,0,0,1 },
            { 0,0,0,0,1,0,0,0,1,1,0,0,1,0,1,1,1,1,1,0,0,1,0 },
            { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1 },
            { 0,1,1,0,1,1,0,0,0,1,1,0,1,1,0,0,0,1,0,0,1,1,0 },
            { 1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,0,0,0,1,0,0,0,0 },
            { 1,1,0,0,1,0,0,1,1,1,1,0,0,1,1,1,1,0,1,0,1,0,0 },
            { 0,0,1,0,0,0,1,1,1,0,1,0,0,0,1,1,0,1,0,1,1,0,1 },
            { 0,0,1,1,0,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,0,0,1 },
            { 0,0,1,0,1,0,1,1,0,1,1,0,1,0,0,1,1,0,1,1,1,0,0 },
            { 0,1,1,0,0,1,0,0,1,0,1,0,0,1,1,0,1,1,1,0,0,0,1 },
            { 1,0,1,0,1,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,1,0,1 },
            { 0,0,0,1,1,0,0,1,0,0,0,1,1,0,1,1,0,1,0,1,1,1,0 },
            { 0,0,1,1,1,0,1,0,1,0,1,1,1,0,0,0,0,0,0,0,1,1,1 },
            { 0,0,0,1,0,0,0,1,1,1,0,1,0,0,0,1,1,0,1,1,0,1,0 },
    };

    // Vectors used to find a shortest possible solution:
    int[] n1 = {1,0,1,0,1,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,1,0,1,0,1};
    int[] n2 = {1,1,0,1,1,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,1,1,0,1,1};
    //


    //VARIABLES END

    //Constructor
    public GameBoard(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        myContext = context;
        scale = myContext.getResources().getDisplayMetrics().density;

        lights = new ArrayList<Light>();


        //stats rectangle bg
        lowerGameBanner = new Paint();
        lowerGameBanner.setColor(Color.rgb(96, 102, 112));
        lowerGameBanner.setStyle(Paint.Style.FILL);
        lowerGameBanner.setAlpha(150);

        bestScoreBanner = new Paint();
        bestScoreBanner.setColor(Color.rgb(41, 45, 53));
        bestScoreBanner.setStyle(Paint.Style.FILL);

        //custom font
        pixelFont = Typeface.createFromAsset(myContext.getAssets(), "llpixel.ttf");
        pixelFontPaint = new Paint();
        pixelFontPaint.setTypeface(pixelFont);
        pixelFontPaint.setAntiAlias(true);
        pixelFontPaint.setColor(Color.WHITE);
        pixelFontPaint.setStyle(Paint.Style.STROKE);
        pixelFontPaint.setTextAlign(Paint.Align.LEFT);
        pixelFontPaint.setTextSize(scale * 20);

        //smaller custom font
        smallPixelPaint = new Paint();
        smallPixelPaint.setTypeface(pixelFont);
        smallPixelPaint.setAntiAlias(true);
        smallPixelPaint.setColor(Color.WHITE);
        smallPixelPaint.setStyle(Paint.Style.STROKE);
        smallPixelPaint.setTextAlign(Paint.Align.RIGHT);
        smallPixelPaint.setTextSize(scale * 15);

        smallRedPixelPaint = new Paint();
        smallRedPixelPaint.setTypeface(pixelFont);
        smallRedPixelPaint.setAntiAlias(true);
        smallRedPixelPaint.setColor(Color.rgb(25, 32, 45));
        smallRedPixelPaint.setStyle(Paint.Style.STROKE);
        smallRedPixelPaint.setTextAlign(Paint.Align.RIGHT);
        smallRedPixelPaint.setTextSize(scale * 15);

        hintTextPaint = new Paint();
        hintTextPaint.setTypeface(pixelFont);
        hintTextPaint.setAntiAlias(true);
        hintTextPaint.setColor(Color.WHITE);
        hintTextPaint.setShadowLayer(20, 0, 0, Color.BLACK);
        hintTextPaint.setStyle(Paint.Style.STROKE);
        hintTextPaint.setTextAlign(Paint.Align.CENTER);
        hintTextPaint.setTextSize(scale * 15);


        smallPixelPaintLeft = new Paint();
        smallPixelPaintLeft.setTypeface(pixelFont);
        smallPixelPaintLeft.setAntiAlias(true);
        smallPixelPaintLeft.setColor(Color.WHITE);
        smallPixelPaintLeft.setStyle(Paint.Style.STROKE);
        smallPixelPaintLeft.setTextAlign(Paint.Align.LEFT);
        smallPixelPaintLeft.setTextSize(scale * 15);

        //top banner bg
        topBannerBg = myContext.getResources().getDrawable(R.drawable.gameboard_topbanner_bg);
        topBannerBg.setAlpha(150);



        //Create the elements needed for the white text:
        whiteTextPaint = new Paint();
        whiteTextPaint.setAntiAlias(true);
        whiteTextPaint.setColor(Color.WHITE);
        whiteTextPaint.setStyle(Paint.Style.STROKE);
        whiteTextPaint.setTextAlign(Paint.Align.LEFT);
        whiteTextPaint.setTextSize(scale*15);


        movesMade = 0;
        currentLevel = 1; //perhaps change this later to return to previously saved progress
        nextLevel = false;

        //Generate the lights array
        generateLightArray();


    }



    //Called only once when the view is created, similar to an onStart() method
    @Override
    public void onSizeChanged (int w, int h, int oldW, int oldH) {
        //Calculate the screen dimensions and set variables for W & H
        super.onSizeChanged(w, h, oldW, oldH);
        screenW = w;
        screenH = h;

        if (LoadLevel.levelToLoad % 3 == 0) {
            mInterstitialAd = new InterstitialAd(myContext);
            mInterstitialAd.setAdUnitId("<AD_ID>");

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    //TODO - probably leave empty or remove?
                }
            });
            requestNewInterstitial();
        }



        //Set title image dimensions
        titleW = w;
        titleH = ((int) smallPixelPaintLeft.getTextSize() * 2) + 40;

        //Load the top logo graphic
        topBannerBg.setBounds(0, 0, titleW, titleH);




        //Load the home icon
        Bitmap homeBitmap = BitmapFactory.decodeResource(myContext.getResources(),
                (R.drawable.home_image));
        homeIcon = Bitmap.createScaledBitmap(homeBitmap, titleH/2, titleH/2, false);


        //Load the lights graphics into the variables
        Bitmap tempBitmap = BitmapFactory.decodeResource(myContext.getResources(),
                (R.drawable.light_off));
        lightsW = ((screenW-60)/5);
        lightsH = lightsW;
        lightOffBmp = Bitmap.createScaledBitmap(tempBitmap, lightsW,
                lightsH, false);
        tempBitmap = BitmapFactory.decodeResource(myContext.getResources(),
                (R.drawable.light_on));
        lightOnBmp = Bitmap.createScaledBitmap(tempBitmap, lightsW,
                lightsH, false);
        tempBitmap = BitmapFactory.decodeResource(myContext.getResources(),
                (R.drawable.light_hint_active));
        lightHintActive = Bitmap.createScaledBitmap(tempBitmap, lightsW,
                lightsH, false);

        //hint button
        tempBitmap = BitmapFactory.decodeResource(myContext.getResources(),
                (R.drawable.hint_green));
        hintGreen = Bitmap.createScaledBitmap(tempBitmap, ((screenW / 7)*2) - 50, (screenW / 7) - 25, false);
        tempBitmap = BitmapFactory.decodeResource(myContext.getResources(),
                (R.drawable.hint_grey));
        hintGrey = Bitmap.createScaledBitmap(tempBitmap, ((screenW / 7)*2) - 50, (screenW / 7) - 25, false);

        resetButton = hintGreen;

        //Set the starting level
        for (int i = 0; i < lights.size(); i++) {
            lights.get(i).setBoolean(false);
        }
        openingLevel = LoadLevel.getLevelToLoad(); //remove when LevelSelector is complete
        currentLevel = openingLevel;
        Levels.setLevel(currentLevel);

        solution();
        checkWinnable();
        lightsRemaining = lightsOnCount();

        // find player best score and player best stars
        sqlDBHelper dbh = new sqlDBHelper(myContext);
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        Cursor c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.TABLE_NAME + " WHERE " +
                sqlTableData.TableInfo.BEST + "='" + 10000 + "'", null);
        c.moveToFirst();

        c = dbh.getInformation(dbh, currentLevel);
        try {
            if (c.getCount() == 0) { //This could possibly be tidied up - c.getCount never equals 0
                Log.d("GameBoard hasWon()", "level not yet completed");
                playerBestStars = 0;
                playerBestScore = 0;
              } else {
                Log.d("GameBoard hasWon()", "level already completed");
                playerBestStars = c.getInt(3);
                playerBestScore = c.getInt(2);
                Log.d("player data populated", Integer.toString(playerBestStars) + " & " +
                        Integer.toString(playerBestScore));
            }
        } catch (Exception e) {
            Log.d("GameBoard OnSizeChanged", "loading the playerBest... info failed");
        }



        c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.HINT_TABLE_NAME + " WHERE " +
                sqlTableData.TableInfo.PLAYER + "=1", null);
        if (c.moveToFirst()) {
            hintsRemaining = c.getInt(1);
        }

        SQ.close();

        parTarget = "target: " + par;


        if (hintsRemaining != 0) {
            hintButton = hintGreen;
        } else {
            hintButton = hintGrey;
        }



    }


    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if ((int) this.getTag() != hintsRemaining && this.getTag() != null) {
                hintsRemaining = (int) this.getTag();
                hintButton = hintGreen;
                this.setTag(null);
            }
        } catch (Exception e) {
            this.setTag(null);
        } finally {
            this.setTag(null);
        }

        //Draw the top banner
        topBannerBg.draw(canvas);

        //draw the home icon
        canvas.drawBitmap(homeIcon, titleH/4, (titleH/4), null);
        //draw level text
        canvas.drawText("LEVEL " + Integer.toString(currentLevel), homeIcon.getWidth()*2,
                (titleH / 2) + (pixelFontPaint.getTextSize() / 3), pixelFontPaint);
        //draw target text
        canvas.drawText(parTarget, (titleW - 20),
                smallPixelPaint.getTextSize() + 10, smallPixelPaint);
        //draw movesmade text and set colour based on parTarget
        if (movesMade <= par) {
            canvas.drawText("moves made: " + Integer.toString(movesMade), (titleW - 20),
                    ((smallPixelPaint.getTextSize() * 2) + 20), smallPixelPaint);
        } else {
            canvas.drawText("moves made: " + Integer.toString(movesMade), (titleW - 20),
                    ((smallRedPixelPaint.getTextSize() * 2) + 20), smallRedPixelPaint);
        }

        //draw the mid banner
        canvas.drawRect(0, titleH + (lightsH*5) + 30,
                screenW, titleH + (lightsH*5) + 30 + screenW/7, lowerGameBanner);

        //draw best info banner
        canvas.drawRect(0, titleH + (lightsH*5) + 30 + screenW/7,
                screenW, titleH + (lightsH*5) + 30 + screenW/7 + screenW/14, bestScoreBanner);

        //draw hint button
        canvas.drawBitmap(hintButton, 30, titleH + (lightsH * 5) + 42, null);

        //draw hint button text
        canvas.drawText("Hint", 30 + (hintButton.getWidth() / 2),
                titleH + (lightsH * 5) + 42 + ((hintButton.getHeight()) / 2) + (hintTextPaint.getTextSize() / 3) ,
                hintTextPaint);

        //draw hints remaining text
        canvas.drawText(hintsRemaining + " hints", (60 + hintButton.getWidth()),
                titleH + (lightsH * 5) + 42 + (hintButton.getHeight() / 2),
                smallPixelPaintLeft);
        canvas.drawText("remaining", (60 + hintButton.getWidth()),
                titleH + (lightsH * 5) + 42 + (hintButton.getHeight() / 2)
                        + smallPixelPaintLeft.getTextSize(),
                smallPixelPaintLeft);

        //draw reset button
        canvas.drawBitmap(resetButton, screenW - resetButton.getWidth() - 30, titleH + (lightsH * 5) + 42, null);
        //draw reset button text
        canvas.drawText("Reset", screenW - 30 - (resetButton.getWidth() / 2),
                titleH + (lightsH * 5) + 42 + ((hintButton.getHeight()) / 2) + (hintTextPaint.getTextSize() / 3) ,
                hintTextPaint);

        //draw best stats text
        if (playerBestScore > 0) {
            canvas.drawText("best score: " + playerBestScore, screenW - 30,
                    titleH + (lightsH * 5) + 30 + screenW / 7 + screenW / 28 +
                            (smallPixelPaint.getTextSize() / 3), smallPixelPaint);
        }

        //Draw the lights grid onto the screen
        int q = 0;
        for (int i = 0; i < lights.size(); i++) {
            boolean p = lights.get(i).getBoolean();
            boolean h = lights.get(i).getHintActive();
            if (i > 0) { q += 1; }
            if (q > 4) { q = 0; }
            if (i < 5) {

                if (p) {
                    canvas.drawBitmap(lightOnBmp, (q * lightsW) + 30, titleH + 15, null);
                } else if (!p) {
                    canvas.drawBitmap(lightOffBmp, (q * lightsW) + 30, titleH + 15, null);
                }
                if (h) {
                    canvas.drawBitmap(lightHintActive, (q * lightsW) + 30, titleH + 15, null);
                }
            }
            if (i > 4 && i < 10) {
                if (p) {
                    canvas.drawBitmap(lightOnBmp, (q * lightsW) + 30, titleH + lightsH + 15, null);
                } else if (!p) {
                    canvas.drawBitmap(lightOffBmp, (q * lightsW) + 30, titleH + lightsH + 15, null);
                }
                if (h) {
                    canvas.drawBitmap(lightHintActive, (q * lightsW) + 30, titleH + lightsH + 15, null);
                }
            }
            if (i > 9 && i < 15) {
                if (p) {
                    canvas.drawBitmap(lightOnBmp, (q * lightsW) + 30, titleH + (lightsH * 2) + 15, null);
                } else if (!p) {
                    canvas.drawBitmap(lightOffBmp, (q * lightsW) + 30, titleH + (lightsH * 2) + 15, null);
                }
                if (h) {
                    canvas.drawBitmap(lightHintActive, (q * lightsW) + 30, titleH + (lightsH * 2) + 15, null);
                }
            }
            if (i > 14 && i < 20) {
                if (p) {
                    canvas.drawBitmap(lightOnBmp, (q * lightsW) + 30, titleH + (lightsH * 3) + 15, null);
                } else if (!p) {
                    canvas.drawBitmap(lightOffBmp, (q * lightsW) + 30, titleH + (lightsH * 3) + 15, null);
                }
                if (h) {
                    canvas.drawBitmap(lightHintActive, (q * lightsW) + 30, titleH + (lightsH * 3) + 15, null);
                }
            }
            if (i > 19 && i < 25) {
                if (p) {
                    canvas.drawBitmap(lightOnBmp, (q * lightsW) + 30, titleH + (lightsH * 4) + 15, null);
                } else if (!p) {
                    canvas.drawBitmap(lightOffBmp, (q * lightsW) + 30, titleH + (lightsH * 4) + 15, null);
                }
                if (h) {
                    canvas.drawBitmap(lightHintActive, (q * lightsW) + 30, titleH + (lightsH * 4) + 15, null);
                }
            }
            if (i > 24) {
                System.out.println("There are too many objects in the lights array - check code");
            }
        }

    }





    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();

        for (int i = 0; i < 5; i++) {
            if (X >= (lightsW*i) + 30 && X < (lightsW*(i+1)) + 30) {tX = i;}
        }
        for (int i = 0; i < 5; i++) {
            if (Y >= titleH + ((lightsH)*i) + 15 && Y < titleH + ((lightsH)*(i+1)) + 15) {tY = i*10;}
        }

        touchedId = tX + tY;
        switch (eventAction ) {
            case MotionEvent.ACTION_DOWN:

                //switch all hints off
                for (int i = 0; i < lights.size(); i++) {
                    lights.get(i).setHintActive(false);
                }

                //Manage lights board
                if (X < (lightsW * 5) + 30 && X > 30
                        && Y < titleH+15 + lightsH*5 &&
                        Y > titleH + 15) {

                    for (int i = 0; i < lights.size(); i++) {
                        int u = lights.get(i).getId();

                        //switch the pressed light
                        if (touchedId == u && Y < titleH + (lightsH * 5) && Y > titleH) {
                            if (!lights.get(i).getBoolean()) {
                                lights.get(i).setBoolean(true);
                                movesMade += 1;
                            } else if (lights.get(i).getBoolean()) {
                                lights.get(i).setBoolean(false);
                                movesMade += 1;
                            }

                            for (int m = 0; m < lights.size(); m++) {
                                int l = lights.get(m).getId();
                                if (l == u - 1 || l == u + 1 || l == u - 10 || l == u + 10) {
                                    if (!lights.get(m).getBoolean()) {
                                        lights.get(m).setBoolean(true);
                                    } else if (lights.get(m).getBoolean()) {
                                        lights.get(m).setBoolean(false);

                                    }
                                }
                            }
                        }
                    }
                }
                //

                lightsRemaining = lightsOnCount();
                nextLevel = hasWon(lightsRemaining);
                int levelXX = 0;
                int starsXX;

                if (nextLevel) {
                    sqlDBHelper dbh = new sqlDBHelper(myContext);
                    SQLiteDatabase SQ = dbh.getReadableDatabase();
                    Cursor c = SQ.rawQuery("SELECT * FROM " + sqlTableData.TableInfo.TABLE_NAME + " WHERE " +
                            sqlTableData.TableInfo.LEVEL + "='" + currentLevel + "'", null);
                    if (c.moveToFirst()) {
                        levelXX = (c.getInt(0));

                    }
                    SQ.close();

                    try {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    } catch (Exception e) {
                        Log.d("interstitial", "failed.");
                    }

                    if (movesMade == par) {
                        starsXX = 3;
                    } else if (movesMade <= par + 5) {
                        starsXX = 2;
                    } else {
                        starsXX = 1;
                    }

                    //Call the level complete dialog
                    if (currentLevel < 80) {
                        final StandardDialog dlg = new StandardDialog(myContext,
                                "Level " + levelXX + " complete",
                                "Score:  " + movesMade + " / " + par,
                                "Retry", "Next", starsXX);
                        dlg.setCanceledOnTouchOutside(false);
                        dlg.setOnKeyListener(new Dialog.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_BACK) {
                                    closeMyActivity();
                                    Log.d("closeMyActivity", "called from onKeyListener");
                                    dlg.dismiss();
                                }
                                return true;
                            }
                        });
                        dlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                        dlg.show();

                        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                switch (dlg.decision) {
                                    case 1:
                                        closeMyActivity();
                                        LoadLevel.setLevelToLoad(currentLevel);
                                        Intent retryIntent = new Intent(myContext, MainActivity.class);
                                        myContext.startActivity(retryIntent);
                                        dlg.dismiss();
                                        break;
                                    case 2:
                                        closeMyActivity();
                                        LoadLevel.setLevelToLoad(currentLevel + 1);
                                        Intent nextIntent = new Intent(myContext, MainActivity.class);
                                        myContext.startActivity(nextIntent);
                                        dlg.dismiss();
                                        break;
                                }
                            }
                        });

                        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                closeMyActivity();
                                Log.d("closeMyActivity", "called from onCancelListener");
                                dlg.dismiss();
                            }
                        });

                    } else {

                        final StandardDialog dlg = new StandardDialog(myContext,
                                "Congratulations!!",
                                "You have completed all of the levels!",
                                "Home", "Choose Level", starsXX);
                        dlg.setCanceledOnTouchOutside(false);
                        dlg.setOnKeyListener(new Dialog.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_BACK) {
                                    closeMyActivity();
                                    Log.d("closeMyActivity", "called from onKeyListener");
                                    dlg.dismiss();
                                }
                                return true;
                            }
                        });
                        dlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                        dlg.show();

                        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                switch (dlg.decision) {
                                    case 1:
                                        closeMyActivity();
                                        Intent retryIntent = new Intent(myContext, StartActivity.class);
                                        myContext.startActivity(retryIntent);
                                        dlg.dismiss();
                                        break;
                                    case 2:
                                        closeMyActivity();
                                        Intent nextIntent = new Intent(myContext, LevelSelector.class);
                                        myContext.startActivity(nextIntent);
                                        dlg.dismiss();
                                        break;
                                }
                            }
                        });

                        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                closeMyActivity();
                                Log.d("closeMyActivity", "called from onCancelListener");
                                dlg.dismiss();
                            }
                        });
                    }
                }

                //Manage home button click
                if (X < titleH && Y < titleH) {
                    //TODO - open dialog warning that user progress will not be saved
                    closeMyActivity();
                }

                //manage hint button
                if (X > 30
                        && X < 30 + hintGreen.getWidth()
                        && Y > (titleH + (lightsH * 5) + 42)
                        && Y < (titleH + (lightsH * 5) + 42) + hintGreen.getWidth() ) {
                    Log.d("Hint", "hint button pressed");

                    hintButton = hintGrey;

                    if (hintsRemaining > 0) {
                        newSolution();
                        for (int i = 0; i < solutionArray.length; i++) {
                            if (solutionArray[i] == 1) {
                                lights.get(i).setHintActive(true);
                                break;
                            }
                        }

                        hintsRemaining -= 1;

                        sqlDBHelper dbh = new sqlDBHelper(myContext);
                        SQLiteDatabase SQ = dbh.getWritableDatabase();
                        SQ.execSQL("UPDATE " + sqlTableData.TableInfo.HINT_TABLE_NAME + " SET " +
                                sqlTableData.TableInfo.HINTS_REMAINING + "='" + hintsRemaining +
                                "' WHERE " + sqlTableData.TableInfo.PLAYER + "='1'");
                        SQ.close();

                    } else {

                        final StandardDialog dlg = new StandardDialog(myContext,
                                "0 hints remaining",
                                "Would you like to buy more?",
                                "No", "Yes", 0);
                        dlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        dlg.show();
                        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {

                                switch (dlg.decision) {
                                    case 1:
                                        dlg.dismiss();
                                        break;

                                    case 2:
                                        dlg.dismiss();

                                        //TODO
                                        ((MainActivity) myContext).beginPurchase();
                                        dlg.dismiss();
                                        break;
                                }


                            }

                        });


                    }
                }

                //manage reset button
                if (X > (screenW - 30 - hintButton.getWidth())
                        && X < screenW - 30
                        && Y > titleH + (lightsH * 5) + 42
                        && Y < titleH + (lightsH * 5) + 42 + hintButton.getHeight()) {
                            resetLevel();
                    }

                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                //put hint button back to green if hints remaining
                if (hintsRemaining > 0) {
                    hintButton = hintGreen;
                }

                break;
        }

        invalidate();
        return true;
    }



//
//ALL MY METHODS:
//


    //Called just once to set up the light array
    private void generateLightArray() {
        lights.clear();
        for (int i=0; i<41; i+=10) {
            for (int j=0; j<5; j++) {
                int tempId = i + j;
                Light tempLight = new Light(tempId);
                lights.add(tempLight);
            }
        }
    }

    //Count number of lights remaining
    private int lightsOnCount() {
        int count = 0;
        for (int i = 0; i < lights.size(); i++) {
            if (lights.get(i).getBoolean()) {
                count += 1;
            }
        }
        return count;
    }

    //method to move the game on if the player wins (lr = lights remaining)
    private boolean hasWon(int lr) {
        if (lr < 1) {

            //save the level stats to the database
            sqlDBHelper dbh = new sqlDBHelper(myContext);

            /*
            check the database table to find out if there's already an entry for the level.
            If so, amend the entry with the new data. If an entry doesn't exist, create a
            new entry.
             */
            if (dbh.getInformation(dbh, currentLevel) != null) {
                dbh.editInformation(dbh, currentLevel, bestScore(), starsBest(playerBestStars));

            } else {
                dbh.putInformation(dbh, currentLevel, par, bestScore(), starsWon());
            }

            return true;
        } else {
            return false;
        }

    }

    //returns the player's best score for the level
    public int bestScore() {
        if (movesMade < playerBestScore && playerBestScore != 0) {
            return movesMade;
        } else if (playerBestScore == 0) {
            return movesMade;
        } else {
            return playerBestScore;
        }
    }

    //determines how many stars to award the player based on score vs par
    public int starsWon() {
        if (movesMade == par) {
            return 3;
        } else if (movesMade > par && movesMade <= par+5) {
            return 2;
        } else if (movesMade > par+5) {
            return 1;
        } else {
            return 0;
        }
    }

    public int starsBest(int stars) {
        if (starsWon() > stars) {
            return starsWon();
        } else {
            return stars;
        }
    }


    //Reset the level to start again
    public void resetLevel() {
        movesMade = 0;

        for (int i = 0; i < lights.size(); i++) {
            lights.get(i).setBoolean(false);
        }

        Levels.setLevel(currentLevel);
        solution();

        lightsRemaining = lightsOnCount();

        Log.d("Level reset:", Integer.toString(currentLevel));

    }

    //method for copying the integers from one array to another
    private void acpy(int[] src, int[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    //Method for adding integers in two arrays then modulo 2
    private void addto(int[] src, int[] v) {
        for (int i = 0; i < v.length; i++) {
            src[i] = (src[i] + v[i]) % 2;
        }
    }

    //method for adding up number of '1's in an int array
    private int wt(int[] v) {
        int t = 0;
        for (int i = 0; i < v.length; i++) {
            t = t + v[i];
        } return t;
    }

    //Big method for finding the best possible solutions
    public void solution() {

        int[] currentState = new int[23];
        int[] hintVector = new int[25];
        int[] best = new int[25];
        int[] next = new int[25];
        int i, j;

        //reset hintVector
        for (i=0; i<25; i++) {
            hintVector[i] = 0;
        }

        //populate currentState with 0s & 1s depending on current lights status
        for (i=0; i<23; i++) {
            if (lights.get(i).getBoolean() ) {
                currentState[i] = 1;
            } else { currentState[i] = 0; }
        }

        //generate the hint vector array
        for (i=0; i<23; i++) {
            for (j=0; j<23; j++) {
                hintVector[i] =
                        (hintVector[i] + currentState[j]*hint_matrix[i][j])%2;
            }
        }

        // Now we have a working hint vector, but we test h+n1, h+n2
        // and h+n1+n2 to see which has the lowest weight, giving a
        // shortest solution.

        //copy the solution to the two arrays, best and next
        acpy(hintVector, best);
        acpy(hintVector, next);

        addto(next, n1);
        if ( wt(next) < wt(best) ) {
            acpy(next, best);
        }
        acpy(hintVector, next);
        addto(next, n2);
        if ( wt(next) < wt(best) ) {
            acpy(next, best);
        }
        acpy(hintVector, next);
        addto(next, n1);
        addto(next, n2);
        if ( wt(next) < wt(best) ) {
            acpy(next, best);
        }

        par = wt(best);

        solutionArray = best;                 // set solution array to the best possible solution
    }

    public void newSolution() {
        int[] currentState = new int[23];
        int[] hintVector = new int[25];
        int[] best = new int[25];
        int[] next = new int[25];
        int i, j;

        //reset hintVector
        for (i=0; i<25; i++) {
            hintVector[i] = 0;
        }

        //populate currentState with 0s & 1s depending on current lights status
        for (i=0; i<23; i++) {
            if (lights.get(i).getBoolean() ) {
                currentState[i] = 1;
            } else { currentState[i] = 0; }
        }

        //generate the hint vector array
        for (i=0; i<23; i++) {
            for (j=0; j<23; j++) {
                hintVector[i] =
                        (hintVector[i] + currentState[j]*hint_matrix[i][j])%2;
            }
        }


        // Now we have a working hint vector, but we test h+n1, h+n2
        // and h+n1+n2 to see which has the lowest weight, giving a
        // shortest solution.

        //copy the solution to the two arrays, best and next
        acpy(hintVector, best);
        acpy(hintVector, next);

        addto(next, n1);
        if ( wt(next) < wt(best) ) {
            acpy(next, best);
        }
        acpy(hintVector, next);
        addto(next, n2);
        if ( wt(next) < wt(best) ) {
            acpy(next, best);
        }
        acpy(hintVector, next);
        addto(next, n1);
        addto(next, n2);
        if ( wt(next) < wt(best) ) {
            acpy(next, best);
        }

        solutionArray = best;
    }

    //Let me know if the sequence is solvable
    private void checkWinnable() {
        if ( currentSolvable() ) {
            System.out.println("This position is winnable.");
        } else {
            System.out.println("This position is not winnable.");
        }
    }

    //check if a sequence is mathematically possible to solve
    private boolean currentSolvable() {
        int[] currentState = new int[25];
        int i,dotprod;

        for (i=0; i<25; i++) {
            if (lights.get(i).getBoolean()) {
                currentState[i] = 1; }
            else {
                currentState[i] = 0; }
        }

        dotprod = 0;
        for (i=0; i<25; i++) {
            dotprod = (dotprod + currentState[i]*n1[i])%2;
        }

        if (dotprod != 0) { return(false); }

        dotprod = 0;
        for (i=0; i<25; i++) {
            dotprod = (dotprod + currentState[i]*n2[i])%2;
        }

        if (dotprod != 0) { return(false); }
        else { return (true); }
    }

    public void closeMyActivity() {
        ((Activity) myContext).finish();
    }

    private void requestNewInterstitial() {

        Log.d("myAds", "requestNewInterstitial called");


        AdColonyBundleBuilder.setZoneId("<ZONE_ID>");

        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdColonyAdapter.class, AdColonyBundleBuilder.build())
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("<TEST_DEVICE_ID>")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
