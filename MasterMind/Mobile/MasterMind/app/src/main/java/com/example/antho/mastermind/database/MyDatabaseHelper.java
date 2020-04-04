package com.example.antho.mastermind.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Massimo on 01/12/2016.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "mastermind.db";
    final static String MASTERMIND_TABLE_NAME = "mastermind";
    final static String MASTERMIND_TABLE_SQL = "CREATE TABLE mastermind (\n" +
            "\t_id\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\tdifficolta\tINTEGER NOT NULL,\n" +
            "\tdata\tDATETIME NOT NULL,\n" +
            "\ttentativi\tINTEGER NOT NULL,\n" +
            "\trisultato\tINTEGER NOT NULL\n" +
            ");";

    final static int version = 1;

    Context gContext;
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
        gContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MASTERMIND_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
