package com.ajsmithsw.switchout;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alexander Smith on 05/07/15.
 */
public class StandardDialog extends Dialog implements View.OnClickListener {

    public Context c;
    public Dialog d;
    public String dlgTitle, message, button1, button2;
    public TextView titleView, messageView;
    public Button button1View, button2View;
    private Typeface pixelFont;
    public int decision;
    public ImageView icon;


    //constructor
    public StandardDialog(Context context, String dlgTitle, String message, String button1, String button2, int icn) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.standard_dialog_layout);

        this.c = context;
        this.dlgTitle = dlgTitle;
        this.message = message;
        this.button1 = button1;
        this.button2 = button2;
        decision = 0;

        pixelFont = Typeface.createFromAsset(c.getAssets(), "llpixel.ttf");

        titleView = (TextView) findViewById(R.id.standard_dialog_title);
        messageView = (TextView) findViewById(R.id.standard_dialog_message);
        button1View = (Button) findViewById(R.id.standard_dialog_button1);
        button2View = (Button) findViewById(R.id.standard_dialog_button2);
        icon = (ImageView) findViewById(R.id.standard_dialog_icon);

        if (icn != 0) {
            switch (icn) {
                case 1:
                    icon.setImageResource(R.drawable.star1);
                    break;

                case 2:
                    icon.setImageResource(R.drawable.star2);
                    break;

                case 3:
                    icon.setImageResource(R.drawable.star3);
                    break;
            }
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleView.setText(dlgTitle);
        messageView.setText(message);
        button1View.setText(button1);
        button2View.setText(button2);

        titleView.setTypeface(pixelFont);
        messageView.setTypeface(pixelFont);
        button1View.setTypeface(pixelFont);
        button2View.setTypeface(pixelFont);

        button1View.setOnClickListener(this);
        button2View.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.standard_dialog_button1:
                decision = 1;
                dismiss();
                break;

            case R.id.standard_dialog_button2:
                decision = 2;
                dismiss();
                break;
        }
    }

}
