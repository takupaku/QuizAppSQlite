package com.example.student.quizappsqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.student.quizappsqlite.model.Question;

import static com.example.student.quizappsqlite.sqlite.DBConstant.*;


public class MyDB extends SQLiteOpenHelper {

    private Context context;



    public MyDB(Context context) {
        super(context, DB_NAME,null,VERSION);
        this.context=context;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            sqLiteDatabase.execSQL(UPDATE_TABLE);
            Toast.makeText(context, "table updated", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(context, "Table not updated", Toast.LENGTH_SHORT).show();

        }


    }

    public boolean insertQuestion(String ques, String op1, String op2,String op3, String op4,String out){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION,ques);
        values.put(option_1,op1);
        values.put(option_2,op2);
        values.put(option_3,op3);
        values.put(option_4,op4);
        values.put(CURRECT_ANS,out);

        if(database.insert(TABLE_NAME,null,values)== -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllQuestion() {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("select * from "+ TABLE_NAME,null);
    }



}
