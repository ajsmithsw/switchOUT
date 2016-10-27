package com.ajsmithsw.switchout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ajsmithsw.switchout.sqlTableData.TableInfo;

/**
 * Created by Alexander Smith on 29/05/15.
 */
public class StartGameOption {

    boolean tableExists;
    sqlDBHelper dbh;
    SQLiteDatabase SQ;
    Cursor c;
    int intToCheck = 10000;
    int levelToReturn;

    public StartGameOption(Context context) {
        dbh = new sqlDBHelper(context);
        SQ = dbh.getReadableDatabase();
        c = null;

        try {
            c = SQ.query(sqlTableData.TableInfo.TABLE_NAME, null, null, null, null, null, null);
            tableExists = true;
            Log.d("StartGameOption", "Table exists");
        } catch (Exception e) {
            tableExists = false;
            Log.d("StartGameOption", "Table doesn't exist yet");
        }

        getLatestLevel();

    }

    public int getLatestLevel() {
        if (!tableExists) {
            LoadLevel.setLevelToLoad(1);
            return 1;

        } else {
            // TO DO: find level id that contains player best as 10000
            c = SQ.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME +
                    " WHERE " + TableInfo.BEST + "="+ intToCheck +"", null);
            c.moveToFirst();
            if (c.moveToFirst()) {
                int ltr = c.getInt(0);
                Log.d("getLatestLevel", Integer.toString(ltr));
            }
            LoadLevel.setLevelToLoad(levelToReturn);
            return levelToReturn;
        }
    }
}
