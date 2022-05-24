package com.example.bai2_61131950;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tho.db";
    public static final String TABLE_NAME = "baitho";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AUTHOR = "Author";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ TABLE_NAME + " ("
                +ID+" integer primary key , "
                +NAME+" VARCHAR , "
                +AUTHOR+" VARCHAR " + ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public int insert(Tho tho){
        int result = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(NAME, tho.getTenBaiTho());
        contentValues.put(AUTHOR, tho.getTenTacGia());
        result = (int) sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        return result ;
    }
    public List<Tho> getListTho() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME ;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        List<Tho> listTho = new ArrayList<>() ;
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex(AUTHOR));
                Tho tho = new Tho(id,name,author) ;
                listTho.add(tho);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  listTho;
    }
}
