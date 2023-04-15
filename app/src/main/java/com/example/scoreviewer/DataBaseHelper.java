package com.example.scoreviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ScoreView";
    public static final String PLAYER_T = "Players";
    public static final String MATCH_T = "Matches";

    public static final String P_COL_1 = "ID";
    public static final String P_COL_2 = "FIRSTNAME";
    public static final String P_COL_3 = "SURNAME";
    public static final String P_COL_4 = "MOBILE";
    public static final String P_COL_5 = "MATCHDATA";


    public static final String M_COL_1 = "ID";
    public static final String M_COL_2 = "WINNER";
    public static final String M_COL_3 = "LOSER";
    public static final String M_COL_4 = "POINTS";
    public static final String M_COL_5 = "WINNER_ID";
    public static final String M_COL_6 = "LOSER_ID";


    DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PLAYER_T + " (ID INTEGER PRIMARY KEY NOT NULL, FIRSTNAME TINYTEXT(25) NOT NULL, SURNAME TINYTEXT(30), MOBILE CHAR(10) NOT NULL, MATCHDATA VARCHAR NOT NULL) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_T );
        onCreate(db);
    }

    boolean insertPlayer(Integer id, String firstname, String surname, String mobile, String matchList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_COL_1, id);
        contentValues.put(P_COL_2, firstname);
        contentValues.put(P_COL_3, surname);
        contentValues.put(P_COL_4, mobile);
        contentValues.put(P_COL_5, matchList);
        long result = db.insert(PLAYER_T, null, contentValues);
        return (result != -1);
    }

    public Cursor getMinPlayerList() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + P_COL_2 + ", " + P_COL_3 + " FROM " + PLAYER_T, null);
    }

}
