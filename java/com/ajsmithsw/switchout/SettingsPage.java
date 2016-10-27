package com.ajsmithsw.switchout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Alexander Smith on 02/07/15.
 */
public class SettingsPage extends Activity {

    private Context myContext;

    ImageView instructionsButton, resetButton, homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settings_layout);

        instructionsButton = (ImageView) findViewById(R.id.settings_settingsbutton);
        resetButton = (ImageView) findViewById(R.id.settings_resetgame);
        homeButton = (ImageView) findViewById(R.id.settings_homebutton);

        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setContentView(R.layout.instructions);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO - have this open a dialog box to ask user to confirm reset

                final StandardDialog areYouSure = new StandardDialog(myContext,
                        "Are you sure?",
                        "Resetting the game will erase all progress and you will lose any unused purchased hints",
                        "No", "Yes", 0);
                areYouSure.setCanceledOnTouchOutside(true);
                areYouSure.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                areYouSure.show();

                areYouSure.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (areYouSure.decision == 2) {
                            try {
                                myContext.deleteDatabase(sqlTableData.TableInfo.DATABASE_NAME);
                                Log.d("Settings Page", "Database deleted");

                                //recreate a fresh database and tables:
                                try {
                                    sqlDBHelper dbhtemp = new sqlDBHelper(myContext);
                                    Log.d("Settings Page", "New database and tables created");
                                } catch (Exception e) {
                                    Log.d("Settings Page", "no database exists");
                                }

                                Toast.makeText(myContext, "Database successfully deleted", Toast.LENGTH_SHORT).show();


                            } catch (Exception e) {
                                Log.d("Settings Page", "Database NOT deleted");
                                Toast.makeText(myContext, "Database NOT deleted", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("Reset", "Game NOT reset.");
                        }
                    }
                });


            }
        });

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
