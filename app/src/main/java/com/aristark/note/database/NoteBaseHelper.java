package com.aristark.note.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aristark on 3/6/16.
 */
public class NoteBaseHelper extends SQLiteOpenHelper {
    public static final int VERSON = 1;
    public static final String DATABASE_NAME = "NoteBase";

    public NoteBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +NoteDbScheme.NoteTable.name+"(" +
                "_id integer primary key autoincrement,"
                +NoteDbScheme.NoteTable.Cols.UUID+","
                +NoteDbScheme.NoteTable.Cols.TITLE+","
                +NoteDbScheme.NoteTable.Cols.CONTENT+","
                +NoteDbScheme.NoteTable.Cols.DATE+","
                +NoteDbScheme.NoteTable.Cols.TAG
                +")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
