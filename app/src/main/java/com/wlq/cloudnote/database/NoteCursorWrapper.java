package com.wlq.cloudnote.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.wlq.cloudnote.Note;

import java.util.Date;
import java.util.UUID;

/**
 * Created by aristark on 3/6/16.
 */
public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor){
        super(cursor);
    }

     public Note getNote(){
         String uuidString = getString(getColumnIndex(NoteDbScheme.NoteTable.Cols.UUID));
         String title = getString(getColumnIndex(NoteDbScheme.NoteTable.Cols.TITLE));
         String content = getString(getColumnIndex(NoteDbScheme.NoteTable.Cols.CONTENT));
         String tag = getString(getColumnIndex(NoteDbScheme.NoteTable.Cols.TAG));
         long date = getLong(getColumnIndex(NoteDbScheme.NoteTable.Cols.DATE));

         Note note = new Note(UUID.fromString(uuidString));
         note.setTitle(title);
         note.setContent(content);
         note.setTag(tag);
         note.setDate(new Date(date));

         return note;
     }
}
