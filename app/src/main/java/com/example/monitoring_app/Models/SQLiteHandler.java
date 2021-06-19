package com.example.monitoring_app.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "test1.db";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_accountTable = "CREATE TABLE IF NOT EXISTS Account(username VARCHAR(20) PRIMARY KEY," +
                "password VARCHAR(20), name VARCHAR(30), mail VARCHAR(30), phone VARCHAR(20), birthday VARCHAR(20)," +
                "address VARCHAR(100))";
        String create_notifyTable = "CREATE TABLE IF NOT EXISTS Notify(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(200), time VARCHAR(50))";
        String create_physicalTable = "CREATE TABLE IF NOT EXISTS Physical(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(200), age VARCHAR(10),height VARCHAR(10),weight VARCHAR(10))";
        String create_currentAcc = "CREATE TABLE IF NOT EXISTS Current(current VARCHAR(20) PRIMARY KEY)";
        db.execSQL(create_currentAcc);
        db.execSQL(create_accountTable);
        db.execSQL(create_notifyTable);
        db.execSQL(create_physicalTable);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);*/
    }
    /**
     * Storing user details in database
     * */
    public void addData(String table,ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = contentValues;
        db.insert(table,null,values);
        db.close();
    }


    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete("Current", null, null);
        db.close();
        Log.d(TAG, "Deleted all user info from sqlite");
    }
    public void setData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(sql,null);
    }
}
