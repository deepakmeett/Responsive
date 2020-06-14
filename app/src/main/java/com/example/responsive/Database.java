package com.example.responsive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String Database = "Database.db";
    public static final String Table = "Database_Table";
    public static final String ID = "Id";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    public Database(@Nullable Context context) {
        super( context, Database, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "create table " + Table + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    public boolean add_data(String Email, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "Email", Email );
        contentValues.put( "Password", Password);
        long result = sqLiteDatabase.insert( Table, null, contentValues );
        return result > 0;
    }

    public boolean check(String Email, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] columns = {ID};
        String selection = EMAIL + "=?" + " AND " + PASSWORD + "=?";
        String[] selectionArgs = {Email, Password};
        
        Cursor cursor = sqLiteDatabase.query( Table, columns, selection, selectionArgs,
                                              null, null, null );
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return cursorCount > 0;
    }
    
    Cursor view_data(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery( "select * from " + Table, null );
    }
}
