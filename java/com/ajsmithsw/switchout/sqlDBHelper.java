package com.ajsmithsw.switchout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ajsmithsw.switchout.sqlTableData.TableInfo;

/**
 * Created by Alexander Smith on 27/05/15.
 */
public class sqlDBHelper extends SQLiteOpenHelper {

    public static final int dbVersion = 1;

    //                                   0      1     2       3
    //New table in database will be: | Level | Par | Best | Stars |
    public String CREATE_LEVELS_TABLE = "CREATE TABLE " + TableInfo.TABLE_NAME +
            "(" + TableInfo.LEVEL + " INT," + TableInfo.PAR + " INT," +
            TableInfo.BEST + " INT," + TableInfo.STARS + " INT);";

    //                                                        0            1
    //Secondary table to store number of hints remaining: | Player | Hints Remaining |
    public String CREATE_HINTS_TABLE = "CREATE TABLE " + TableInfo.HINT_TABLE_NAME +
            "(" + TableInfo.PLAYER + " INT," + TableInfo.HINTS_REMAINING + " INT);";

    //
    //Third, store whether player has redeemed social media bonuses
    public String CREATE_BONUS_TABLE = "CREATE TABLE " + TableInfo.BONUS_TABLE_NAME +
            "(" + TableInfo.PLAYER_BONUS + " INT," + TableInfo.FACEBOOK_BONUS + " INT," +
            TableInfo.TWITTER_BONUS + " INT);";


    //Constructor:
    public sqlDBHelper(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        int startHintCount = 10;                                                 //Set start number of hints

        //If a table doesn't exist, this creates a new table in the database
        db.execSQL(CREATE_LEVELS_TABLE);
        db.execSQL(CREATE_HINTS_TABLE);
        db.execSQL(CREATE_BONUS_TABLE);

        db.execSQL("INSERT INTO " + TableInfo.HINT_TABLE_NAME + " VALUES('1', '" +
        startHintCount + "');");

        db.execSQL("INSERT INTO " + TableInfo.BONUS_TABLE_NAME +
                " VALUES('1', '0', '0');");



        Log.d("sqlDBHelper", "New database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(sqlDBHelper dbh, int level, int par, int best, int stars) {
        SQLiteDatabase SQ = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.LEVEL, level);
        cv.put(TableInfo.PAR, par);
        cv.put(TableInfo.BEST, best);
        cv.put(TableInfo.STARS, stars);
        long k = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.d("sqlDBHelper", "new level information added to db table");
        SQ.close();
    }

    public Cursor getInformation(sqlDBHelper dbh, int currLevel) {
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        //String[] Columns = {TableInfo.LEVEL, TableInfo.PAR, TableInfo.BEST, TableInfo.STARS};
        Cursor c = SQ.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME + " WHERE " +
                TableInfo.LEVEL + "='" + currLevel + "'", null);
        if (c.moveToFirst()) {
            return c;
        } else {
            return null;
        }
    }

    public void editInformation(sqlDBHelper dbh, int level, int best, int stars) {
        SQLiteDatabase SQ = dbh.getWritableDatabase();
        SQ.execSQL("UPDATE " + TableInfo.TABLE_NAME + " SET " + TableInfo.BEST + "='" +
                best + "'," + TableInfo.STARS + "='" + stars + "' WHERE " +
                TableInfo.LEVEL + "='" + level + "'");
        Log.d("sqlDbHelper", "table data edited");
        SQ.close();
    }

}
