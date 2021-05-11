package com.example.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "BD.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors("+
                "id_author integer primary key,"+
                "name text,"+
                "address text,"+
                "email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");
        onCreate(sqLiteDatabase);
    }

    public int insertAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author", author.getId_author());
        contentValues.put("name", author.getName());
        contentValues.put("address", author.getAddress());
        contentValues.put("email", author.getEmail());
        int result =(int)db.insert("Authors",null , contentValues);
        db.close();
        return result;
    }

    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null);
        if (cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            list.add(new Author(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3)));
                    cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Author> getIdAuthor(){

        return null;
    }
}
