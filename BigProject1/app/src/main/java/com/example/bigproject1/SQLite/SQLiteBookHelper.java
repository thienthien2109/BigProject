package com.example.bigproject1.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bigproject1.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class SQLiteBookHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="bookDB.db";
    private static final int DATABASE_VERSION=1;

    public SQLiteBookHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Createsql =" CREATE TABLE book(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT ," +
                "author TEXT,"+
                "type TEXT,"+
                "date TEXT,"+
                "price REAL"+
                ")";
        db.execSQL(Createsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addBook(Book b){
        ContentValues v = new ContentValues();
        v.put("title",b.getTitle());
        v.put("author",b.getAuthor());
        v.put("type",b.getType());
        v.put("date",b.getDate());
        v.put("price",b.getPrice());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("book",null,v);
    }

    public int update(Book b){
        ContentValues v = new ContentValues();
        v.put("title",b.getTitle());
        v.put("author",b.getAuthor());
        v.put("type",b.getType());
        v.put("date",b.getDate());
        v.put("price",b.getPrice());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(b.getId())};
        return st.update("book",v,whereClause,whereArgs);
    }

    public int delete(int id){
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(id)};
        return st.delete("book",whereClause,whereArgs);
    }

    public List<Book> getAll(){
        List<Book> listBook = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor c = st.query("book",null,null,
                null,null,null,null);
        while (c!=null&& c.moveToNext()){
            int id = c.getInt(0);
            String title = c.getString(1);
            String author = c.getString(2);
            String type = c.getString(3);
            String date = c.getString(4);
            Double price = c.getDouble(5);
            Book b  = new Book(id,title,author,type,date,price);
            listBook.add(b);
        }
        return listBook;
    }

    public List<Book> search(String s) {
        List<Book> listBook = new ArrayList<>();
        String whereClause = "title LIKE ?";
        String[] whereArgs = {"%" + s + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor c = st.query("book", null, whereClause, whereArgs
                , null, null, null);
        while (c != null && c.moveToNext()) {
            int id = c.getInt(0);
            String title = c.getString(1);
            String author = c.getString(2);
            String type = c.getString(3);
            String date = c.getString(4);
            Double price = c.getDouble(5);
            Book b = new Book(id, title, author, type, date, price);
            listBook.add(b);
        }
        return listBook;
    }
}
