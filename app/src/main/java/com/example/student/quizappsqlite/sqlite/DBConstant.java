package com.example.student.quizappsqlite.sqlite;

public class DBConstant {
    protected static final String DB_NAME = "my_db.db";
    protected static final int VERSION = 1;

    protected static final String TABLE_NAME = "db_table";
    protected static final String ID = "id";
    protected static final String QUESTION = "question";
    protected static final String option_1 = "op1";
    protected static final String option_2 = "op2";
    protected static final String option_3 = "op3";
    protected static final String option_4 = "op4";
    protected static final String CURRECT_ANS = "currect";


    protected static final String CREATE_TABLE= "create table "+ TABLE_NAME
            +" ( "+ID+" integer primary key autoincrement , "+
            ""+
            " "+ QUESTION+ " test , "+ option_1+" text , "+ option_2 +" text , "+
" "+option_3+ " text , "+option_4+" text , "+CURRECT_ANS+" text )";




protected static final String UPDATE_TABLE="alter table "+ TABLE_NAME+"add column newcolumn text";
}