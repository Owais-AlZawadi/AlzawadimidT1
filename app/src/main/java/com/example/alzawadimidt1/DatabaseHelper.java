package com.example.alzawadimidt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "FNAME";
    public static final String COL3 = "SNAME";
    public static final String COL4 = "NID";
    public static final String COL5 = "MPHONE";
    public static final String COL6 = "LPHONE";

    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID TEXT PRIMARY KEY, " +
                " FNAME TEXT, SNAME TEXT, NID TEXT, MPHONE TEXT, LPHONE TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); }
    /* Basic function to add data. REMEMBER: The fields
       here, must be in accordance with those in
       the onCreate method above.
    */
    public boolean addData(String id, String fname, String sname, String nid, String mphone, String lphone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, fname);
        contentValues.put(COL3, sname);
        contentValues.put(COL4, nid);
        contentValues.put(COL5, mphone);
        contentValues.put(COL6, lphone);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //if data are inserted incorrectly, it will return -1
        if(result == -1) {return false;} else {return true;}
    }


    public boolean Delete(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ? OR FNAME = ? OR SNAME = ? OR NID = ? OR MPHONE = ? OR LPHONE = ?", new String[] {text})>0;
    }

    /* Returns only one result */
    public Cursor structuredQuery(int ID) {
        SQLiteDatabase db = this.getReadableDatabase(); // No need to write
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL1, COL2, COL3, COL4, COL5}, COL1 + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;

    }

    public Cursor getSpecificResult(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE ID = ?", new String[]{id});
        if (data != null)
            data.moveToFirst();
        return data;
    }

    // Return everything inside the dB
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
