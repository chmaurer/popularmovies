package com.chmaurer.android.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chmaurer.android.popularmovies.data.MovieContract.MovieEntry;

/**
 Created by Christian on 12.12.2015.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie2.db";
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     The method creates a new table for storing movies

     @param sqLiteDatabase
     */
    @Override public void onCreate (SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                MovieEntry.COLUMN_ID + " TEXT PRIMARY KEY" +
                " );";


        sqLiteDatabase.execSQL (SQL_CREATE_MOVIE_TABLE);
    }

    /**
     On upgrading the version, we keep the favmovie table, in order not to lose our favourites. The
     link between the two tables is the movie id, so

     @param sqLiteDatabase
     @param oldVersion
     @param newVersion
     */
    @Override public void onUpgrade (SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL ("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate (sqLiteDatabase);
    }
}
