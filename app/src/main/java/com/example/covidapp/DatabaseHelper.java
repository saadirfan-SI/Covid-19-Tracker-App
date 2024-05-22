package com.example.covidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// This class is bassically for the database

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Information
    public static final String DBName = "register.db";

    public DatabaseHelper(@Nullable Context context){
        super(context, DBName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, passsword TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean insertData(String username, String password, String s){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);

        if (result==-1) return false;
        else return true;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

}
