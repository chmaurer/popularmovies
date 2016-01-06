package com.chmaurer.android.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chmaurer.android.popularmovies.data.MovieContract.FavouriteMovieEntry;
import com.chmaurer.android.popularmovies.data.MovieContract.MovieEntry;

/**
 Created by Christian on 12.12.2015.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie.db";
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
                MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieEntry.COLUMN_ORIG_TITLE + " TEXT UNIQUE NOT NULL, " +
                MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POPULARITY + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL " +
                MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL " +
                MovieEntry.COLUMN_VOTE_AVG + " TEXT NOT NULL " +
                " );";

        // We are creating a "favmovie" table, which is basically a duplication of the "movie" table.
        // We do this to make sure that our favourite movies can be stored forever, even when the api
        // does not return them anymore at some point in the future.
        final String SQL_CREATE_FAVMOVIE_TABLE = "CREATE TABLE " + FavouriteMovieEntry.TABLE_NAME + " (" +
                MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieEntry.COLUMN_ORIG_TITLE + " TEXT UNIQUE NOT NULL, " +
                MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POPULARITY + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL " +
                MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL " +
                MovieEntry.COLUMN_VOTE_AVG + " TEXT NOT NULL " +
                FavouriteMovieEntry.COLUMN_ADDED_TO_FAVMOVIES_DATE + " TEXT NOT NULL " +
                FavouriteMovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL " +
                " );";

        sqLiteDatabase.execSQL (SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL (SQL_CREATE_FAVMOVIE_TABLE);
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
