package com.ajsmithsw.switchout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajsmithsw.switchout.sqlTableData.TableInfo;

import java.util.ArrayList;
import java.util.List;


public class LevelSelector extends Activity {

    private List<Progress> myProgress = new ArrayList<Progress>();

    Typeface pixelFont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.level_grid_view);

        populateLevelsGrid();
        populateGridView();

        pixelFont = Typeface.createFromAsset(this.getAssets(), "llpixel.ttf");
        TextView title = (TextView) findViewById(R.id.textView);
        title.setTypeface(pixelFont);
        title.setTextSize(20);

    }

    private void populateLevelsGrid() {
        myProgress.clear();


        //Determine number of entries in the database table
        sqlDBHelper dbh = new sqlDBHelper(this);
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        long numEntries = DatabaseUtils.queryNumEntries(SQ, TableInfo.TABLE_NAME);
        int numEntInt = (int) numEntries;

        for (int i = 1; i < 81; i++) { //TODO - set number of levels

            Cursor c = dbh.getInformation(dbh, i);
            int d = 0;
            try {
                d = c.getCount();
                //Log.d("populateLevelsGrid", "ok");
            } catch (Exception e) {
                //Log.d("populateLevelsGrid", "no entry in database table");
            }

            if (d != 0 ) {
                //Log.d("GameBoard hasWon()", "level not yet completed");
                Progress tempProg = new Progress();
                tempProg.setLevelId(i);
                tempProg.setBestPossible(c.getInt(1));
                tempProg.setBestResult(c.getInt(2));
                tempProg.setStars(c.getInt(3));
                myProgress.add(tempProg);
            } else if (i == numEntInt + 1 && numEntInt + 1 < 81) {
                    //Log.d("GameBoard hasWon()", "level already completed");
                    Progress tempProg = new Progress();
                    tempProg.setLevelId(i);
                    tempProg.setBestPossible(10000);
                    tempProg.setBestResult(10000);
                    tempProg.setStars(0);
                    myProgress.add(tempProg);
                } else {
                    Progress tempProg = new Progress();
                    tempProg.setLevelId(i);
                    myProgress.add(tempProg);
                }

        }

        SQ.close();


        int mypCount = myProgress.size();
        Log.d("Count of array", Integer.toString(mypCount));

    }

    private void populateGridView() {
        ArrayAdapter<Progress> adapter = new MyListAdapter();
        GridView grid = (GridView) findViewById(R.id.level_selection);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Progress clickedProgress = myProgress.get(position);
                LoadLevel.setLevelToLoad(clickedProgress.getLevelId());
                if (clickedProgress.getBestPossible() != 0) {
                    openGameBoard();
                }

            }
        }) ;


    }

    public void openGameBoard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private class MyListAdapter extends ArrayAdapter<Progress> {

        public MyListAdapter() {
            super(LevelSelector.this, R.layout.level_square, myProgress);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.level_square, parent, false);
            }

            Progress currentProgress = myProgress.get(position);
            TextView levelText = (TextView) itemView.findViewById(R.id.level_text);
            levelText.setText("Level "+ currentProgress.getLevelId());
            ImageView starImage = (ImageView) itemView.findViewById(R.id.star_image);
            TextView bestText = (TextView) itemView.findViewById(R.id.best_text);
            Log.d("getView", Integer.toString(currentProgress.getBestResult()));

            if (currentProgress.getBestPossible() != 10000 && currentProgress.getBestResult() != 0) {
                switch (currentProgress.getStars()) {
                    //case 0:
                      //starImage.setImageResource(R.drawable.star0);
                      //break;
                    case 1:
                        starImage.setImageResource(R.drawable.star1);
                        break;
                    case 2:
                        starImage.setImageResource(R.drawable.star2);
                        break;
                    case 3:
                        starImage.setImageResource(R.drawable.star3);
                        break;
                }
                bestText.setText(currentProgress.getBestResult() + " / " + currentProgress.getBestPossible());
            } else if (currentProgress.getBestPossible() == 10000) {
                starImage.setImageResource(R.drawable.star0);
                bestText.setText("");
            } else {
                starImage.setImageResource(R.drawable.padlock);
                bestText.setText("");
            }
            //https://www.youtube.com/watch?v=WRANgDgM2Zg

            return itemView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateLevelsGrid();
        populateGridView();
    }
}
