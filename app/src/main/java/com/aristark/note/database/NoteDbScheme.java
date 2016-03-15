package com.aristark.note.database;

/**
 * Created by aristark on 3/6/16.
 */
public class NoteDbScheme {
    public static final class NoteTable{
        public static final String name = "notes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
            public static final String DATE = "date";
            public static final String TAG = "tag";

        }
    }
}
