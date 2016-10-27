package com.ajsmithsw.switchout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alexander Smith on 12/06/15.
 */
public class LevelCompleteDialog extends Dialog implements View.OnClickListener {

    public Context c;
    public Dialog d;
    public ImageButton retry, next, backToHome;
    public int decision;
    private int currLevel, stars;
    public Bitmap star1, star2, star3;
    public int dStarsW, dStarsH;

    Bitmap displayBmp;


    public LevelCompleteDialog(Context a, int currLevel, int stars) {
        super(a);
        this.c = a;
        decision = 0;
        this.currLevel = currLevel;
        this.stars = stars;

        dStarsW = 100;
        dStarsH = 30;

        Bitmap bmp = BitmapFactory.decodeResource(c.getResources(), (R.drawable.star1));
        star1 = Bitmap.createScaledBitmap(bmp, dStarsW, dStarsH, false);

        bmp = BitmapFactory.decodeResource(c.getResources(), (R.drawable.star2));
        star2 = Bitmap.createScaledBitmap(bmp, dStarsW, dStarsH, false);

        bmp = BitmapFactory.decodeResource(c.getResources(), (R.drawable.star3));
        star3 = Bitmap.createScaledBitmap(bmp, dStarsW, dStarsH, false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.level_complete_dialog);


        retry = (ImageButton) findViewById(R.id.dialog_retry_button);
        next = (ImageButton) findViewById(R.id.dialog_next__button);
        backToHome = (ImageButton) findViewById(R.id.dialog_home_button);
        retry.setOnClickListener(this);
        next.setOnClickListener(this);
        backToHome.setOnClickListener(this);

        TextView dialogText = (TextView) findViewById(R.id.lvl);
        dialogText.setText("Level " + currLevel + " complete!");

        //Draw stars //
        switch (stars) {
            case 1:
                displayBmp = star1;
                break;
            case 2:
                displayBmp = star2;
                break;
            case 3:
                displayBmp = star3;
                break;
        }
        ImageView dialogStarsImage = (ImageView) findViewById(R.id.dialog_stars);
        dialogStarsImage.setImageBitmap(displayBmp);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_retry_button:
                decision = 1;
                dismiss();
                break;

            case R.id.dialog_next__button:
                decision = 2;
                dismiss();
                break;

            case R.id.dialog_home_button:
                decision = 3;
                dismiss();
                break;



        }

    }

}
