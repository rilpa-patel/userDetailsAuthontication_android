package com.example.tabviewwithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String dataBaseName = "StudentInfo";
    static final String tableName = "StudentData";

    public DatabaseHelper(@Nullable Context context) {
        super(context, dataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+tableName+"(id INTEGER PRIMARY KEY AUTOINCREMENT, nameofstudent TEXT NOT NULL, address TEXT NOT NULL, dob TEXT NOT NULL, familyannualincome TEXT NOT NULL, weaksubjects TEXT NOT NULL, strongsubject TEXT NOT NULL, achievements TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String nameofstudent, String address, String dob, String familyannualincome, String weaksubjects, String strongsubject, String achievements){
        SQLiteDatabase db = (SQLiteDatabase) getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nameofstudent", nameofstudent);
        values.put("address", address);
        values.put("dob", dob);
        values.put("familyannualincome", familyannualincome);
        values.put("weaksubjects", weaksubjects);
        values.put("strongsubject", strongsubject);
        values.put("achievements", achievements);

        long l = db.insert(tableName, null, values);

        if(l <= -1) {
            return false;
        } else {

            return  true;
        }
    }

    public boolean update(String id,String nameofstudent, String address, String dob, String familyannualincome, String weaksubjects, String strongsubject, String achievements){
        SQLiteDatabase db = (SQLiteDatabase) getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nameofstudent", nameofstudent);
        values.put("address", address);
        values.put("dob", dob);
        values.put("familyannualincome", familyannualincome);
        values.put("weaksubjects", weaksubjects);
        values.put("strongsubject", strongsubject);
        values.put("achievements", achievements);

        long l = db.update(tableName, values, "id=?", new String[]{id});

        if(l <= -1) {
            return false;
        } else {
            return  true;
        }
    }

    public ArrayList<String> allNamesArray(){
        ArrayList<String> allNames = new ArrayList<String>();

        SQLiteDatabase db = (SQLiteDatabase) getReadableDatabase();

        Cursor c = db.rawQuery("select nameofstudent from "+tableName, null);

        if(c.getCount() <= 0){
            allNames.add("Nothing to see yet, insert something first.");
            return allNames;
        } else{
            while (c.moveToNext()){
                allNames.add(c.getString(0));
            }
            return allNames;
        }
    }

    public Cursor getDataById(String id){
        SQLiteDatabase db = (SQLiteDatabase) getReadableDatabase();

        return db.rawQuery("select * from "+tableName+" where id="+id, null);
    }

    public int totalRows(){
        SQLiteDatabase db = (SQLiteDatabase) getReadableDatabase();

        Cursor c = db.rawQuery("select id from "+tableName, null);

        return c.getCount();
    }
    
}
