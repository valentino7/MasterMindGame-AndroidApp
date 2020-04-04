package com.example.antho.mastermind.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class DAO {
    MyDatabaseHelper dbh;
    SQLiteDatabase database;

    public DAO(Context context) {
        dbh = new MyDatabaseHelper(context);
    }
    public void open() {
        database = dbh.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public boolean insert( Partita p){
        this.open();
        ContentValues c = new ContentValues();
        c.put("difficolta",p.getDifficolta());
        c.put("data",p.getData());
        c.put("tentativi",p.getTentativi());
        c.put("risultato",p.getRisultsato());
        database.insert("mastermind",null,c);
        return true;
    }


    public ArrayList<Partita> getPartita() {
        this.open();
        ArrayList<Partita> arrayPartite = new ArrayList<>();
        Cursor ris = database.rawQuery("select * from mastermind",null);
        ris.moveToFirst();

        while (!ris.isAfterLast()){
            Partita p = new Partita(ris.getInt(ris.getColumnIndex("difficolta")) ,   ris.getString(ris.getColumnIndex("data")),
                    ris.getInt(ris.getColumnIndex("risultato"))   , ris.getInt(ris.getColumnIndex("tentativi")));
            p.setId( ris.getInt(ris.getColumnIndex("_id")));
            arrayPartite.add(p);
            ris.moveToNext();
        }
        return arrayPartite;
    }

}
