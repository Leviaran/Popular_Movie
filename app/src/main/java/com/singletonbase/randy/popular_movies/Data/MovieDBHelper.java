package com.singletonbase.randy.popular_movies.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by randy on 30/05/17.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "movie.db";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieList.MovieEntry.TABLE_NAME + " (" +
                MovieList.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MovieList.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                MovieList.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieList.MovieEntry.COLUMN_IMAGE + " TEXT, " +
                MovieList.MovieEntry.COLUMN_IMAGE2 + " TEXT, " +
                MovieList.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
                MovieList.MovieEntry.COLUMN_RATING + " INTEGER, " +
                MovieList.MovieEntry.COLUMN_DATE + " TEXT, " +
                MovieList.MovieEntry.COLUMN_POPULARITY + " TEXT);";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieList.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
