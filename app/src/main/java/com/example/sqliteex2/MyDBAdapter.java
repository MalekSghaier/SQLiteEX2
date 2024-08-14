package com.example.sqliteex2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDBAdapter {

    Context context;
    private myDBHelper dbHelper;
    private SQLiteDatabase db;
    private  String DATABASE_NAME ="data";
    private  int DATABASE_Version =1;
    public MyDBAdapter(Context context){
        this.context=context;
        dbHelper=new myDBHelper(context,DATABASE_NAME,null,DATABASE_Version);

    }
    public void open(){
        db=dbHelper.getWritableDatabase();
    }
    public void insertStudent(String name,int faculty){
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("faculty",faculty);
        db.insert("students",null,cv);
    }
    public ArrayList<String> selectAllStudents(){
        ArrayList<String>allStudents =new ArrayList<String>();
        Cursor cursor=db.query("students",null,null,null,null,null,null);
        if (cursor!=null && cursor.moveToFirst()){
            do{
                allStudents.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        return allStudents;
    }
    public void deleteALLEpiTec(){
        db.delete("students","faculty=1",null);
    }}

