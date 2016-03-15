package com.wlq.cloudnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wlq.cloudnote.database.NoteBaseHelper;
import com.wlq.cloudnote.database.NoteCursorWrapper;
import com.wlq.cloudnote.database.NoteDbScheme;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by aristark on 3/3/16.
 */
public class NoteLab {
    private static NoteLab sNoteLab; //for the global use
//    private ArrayList<Note> notes;
    private Context context;
    private SQLiteDatabase database;

    private NoteLab(Context c){
//        notes = new ArrayList<Note>();
        this.context = c;
        database =  new NoteBaseHelper(context).getWritableDatabase();


        //generate 100 Note Objects
//        for (int i=0;i<100;i++){
//            Note note = new Note();
//            note.setTitle("this is title "+i);
//            note.setContent("this is content "+i+"balabalabalabalalabalabalabalabalala\nbalabalabalabalalabalabalabalabalala\nbalabalabalabalalabalabalabalabalala\nbalabalabalabalalabalabalabalabalala\nbalabalabalabalalabalabalabalabalala\n");
//            notes.add(note);
//        }
    }

    public static NoteLab getNoteLab(Context context){
        if (sNoteLab == null){
            sNoteLab = new NoteLab(context);
        }

        return sNoteLab;
    }

    public ArrayList<Note> getNotes() {
//        return notes;
        ArrayList<Note> notes = new ArrayList<Note>();
        NoteCursorWrapper wrapper = queryNote(null,null);
        try{
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()){
                notes.add(wrapper.getNote());
                wrapper.moveToNext();
            }
        }finally {
            wrapper.close();
        }

        return notes;
    }

    public void addNote(Note note){
//        notes.add(note);
        ContentValues values = getValues(note);
        database.insert(NoteDbScheme.NoteTable.name,null,values);
    }

    public Note getNote(UUID uuid){
//        for (Note note : notes){
//            if (note.getUuid().equals(uuid)){
//                return note;
//            }
//        }

//        return null;

        NoteCursorWrapper cursorWrapper =  queryNote(NoteDbScheme.NoteTable.Cols.UUID+"=?",new String[]{uuid.toString()});

        try {
            if (cursorWrapper.getCount() == 0){
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getNote();
        }finally {
            cursorWrapper.close();
        }


    }

    private ContentValues getValues(Note note){
        ContentValues values = new ContentValues();
        values.put(NoteDbScheme.NoteTable.Cols.UUID,note.getUuid().toString());
        values.put(NoteDbScheme.NoteTable.Cols.TITLE,note.getTitle());
        values.put(NoteDbScheme.NoteTable.Cols.CONTENT,note.getContent());
        values.put(NoteDbScheme.NoteTable.Cols.DATE,note.getDate().toString());
        values.put(NoteDbScheme.NoteTable.Cols.TAG,note.getTag());
        return values;
    }



    private NoteCursorWrapper queryNote(String whereClause, String[] whereArgs){
        Cursor cursor = database.query(
                NoteDbScheme.NoteTable.name,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new NoteCursorWrapper(cursor);
    }
}