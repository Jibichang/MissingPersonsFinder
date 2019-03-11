package com.example.aomek.missingpersonsfinder.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "member.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "member";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PLACE = "place";
    public static final String COL_PHONE = "phone";

    private static final String SQL_CREATE_TABLE_MEMBER
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_NAME + " TEXT,"
            + COL_EMAIL + " TEXT,"
            + COL_PLACE + " TEXT,"
            + COL_PHONE + " TEXT"
            + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MEMBER);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "WARUNEE");
        cv.put(COL_EMAIL, "aomekkla@gmail.com");
        cv.put(COL_PHONE, "0828543654");
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
