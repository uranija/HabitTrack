package com.example.android.habittrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for HabitsTracker. Manages database creation and version management.
 */

public class HabitContract extends SQLiteOpenHelper {


    public static final String LOG_TAG = HabitContract.class.getSimpleName();

    /**
     * Name of the database file
     */
    public static final String DATABASE_NAME = "HabitTrack";
    /**
     * Database version
     */
    public static final String TABLE_NAME = "cycling_table";


    public static final String COL_1 = "ID";
    /**
     * Date when the habit has taken place
     */
    public static final String COL_2 = "DAYNAME";
    /**
     * Distance the habit has been practiced
     */
    public static final String COL_3 = "MILES";
    /**
     * Calories burned when the habit has been practiced
     */
    public static final String COL_4 = "CALORIES";

    /**
     * Default Constructor
     *
     * @param context
     */
    public HabitContract(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * This method is called when the database is created for the first time
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MILES INTEGER,CALORIES DOUBLE)");
    }

    /**
     * This method is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * This method inserts records in the Habits table
     *
     * @param name     - Name of the day (e.g. monday)
     * @param miles    - the distance (e.g. 30miles)
     * @param calories - calories burned (e.g. 500 calories)
     */
    public boolean insertData(String name, int miles, double calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, miles);
        contentValues.put(COL_4, calories);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    }