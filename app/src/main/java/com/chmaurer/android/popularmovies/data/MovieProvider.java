package com.chmaurer.android.popularmovies.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


/**
 Created by Christian on 12.12.2015.
 */
public class MovieProvider extends ContentProvider {

    static final int MOVIE = 100;
    static final int FAVOURITE_MOVIE = 200;
    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher ();
    private static final SQLiteQueryBuilder sMovieQueryBuilder;
    //prep statement for selecting a movie by id: movie.id = ?
    private static final String sMovieSelection = MovieContract.MovieEntry.TABLE_NAME + "." + MovieContract.MovieEntry.COLUMN_ID + " = ? ";
    //prep statement for selecting a favourite movie by id: favmovie.id = ?
    private static final String sFavouriteMovieSelection = MovieContract.FavouriteMovieEntry.TABLE_NAME + "." + MovieContract.FavouriteMovieEntry.COLUMN_ID + " = ? ";

    static {
        sMovieQueryBuilder = new SQLiteQueryBuilder ();
        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sMovieQueryBuilder.setTables (MovieContract.MovieEntry.TABLE_NAME + " INNER JOIN " + MovieContract.FavouriteMovieEntry.TABLE_NAME + " ON " + MovieContract.MovieEntry.TABLE_NAME + "." +
                MovieContract.MovieEntry.COLUMN_ID + " = " + MovieContract.FavouriteMovieEntry.TABLE_NAME + "." + MovieContract.FavouriteMovieEntry.COLUMN_MOVIE_ID);
    }

    private MovieDbHelper mOpenHelper;

    static UriMatcher buildUriMatcher () {
        // I know what you're thinking.  Why create a UriMatcher when you can use regular
        // expressions instead?  Because you're not crazy, that's why.

        // All paths added to the UriMatcher have a corresponding code to return when a match is
        // found.  The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher (UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI (authority, MovieContract.PATH_MOVIES, MOVIE);
        matcher.addURI (authority, MovieContract.PATH_FAVOURITE_MOVIES, FAVOURITE_MOVIE);
        return matcher;
    }

    private Cursor getMovieById (Uri uri, String[] projection, String sortOrder) {
        String id = MovieContract.MovieEntry.getIdFromUri (uri); // TODO this has to return the id from the uri
        String[] selectionArgs = new String[]{id};
        String selection = sMovieSelection;
        return sMovieQueryBuilder.query (mOpenHelper.getReadableDatabase (), projection, selection, selectionArgs, null, null, sortOrder);
    }

    private Cursor getFavouriteMovieById (Uri uri, String[] projection, String sortOrder) {
        String id = MovieContract.FavouriteMovieEntry.getIdFromUri (uri); // TODO this has to return the id from the uri
        String[] selectionArgs = new String[]{id};
        String selection = sMovieSelection;
        return sMovieQueryBuilder.query (mOpenHelper.getReadableDatabase (), projection, selection, selectionArgs, null, null, sortOrder);
    }

    /*
        Students: We've coded this for you.  We just create a new WeatherDbHelper for later use
        here.
     */
    @Override public boolean onCreate () {
        mOpenHelper = new MovieDbHelper (getContext ());
        return true;
    }

    /*
        Students: Here's where you'll code the getType function that uses the UriMatcher.  You can
        test this by uncommenting testGetType in TestProvider.

     */
    @Override public String getType (Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match (uri);

        switch (match) {
            case MOVIE:
                return MovieContract.MovieEntry.CONTENT_TYPE;
            case FAVOURITE_MOVIE:
                return MovieContract.FavouriteMovieEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException ("Unknown uri: " + uri);
        }
    }

    @Override public Cursor query (Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match (uri)) {
            case MOVIE: {
                retCursor = mOpenHelper.getReadableDatabase ().query (MovieContract.MovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case FAVOURITE_MOVIE: {
                retCursor = mOpenHelper.getReadableDatabase ().query (MovieContract.FavouriteMovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException ("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri (getContext ().getContentResolver (), uri);
        return retCursor;
    }

    @Override public Uri insert (Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase ();
        final int match = sUriMatcher.match (uri);
        Uri returnUri;

        switch (match) {
            case MOVIE: {
                long _id = db.insert (MovieContract.MovieEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = MovieContract.MovieEntry.buildMovieUri (_id);
                } else {
                    throw new android.database.SQLException ("Failed to insert row into " + uri);
                }
                break;
            }
            case FAVOURITE_MOVIE: {
                long _id = db.insert (MovieContract.FavouriteMovieEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = MovieContract.FavouriteMovieEntry.buildMovieUri (_id);
                } else {
                    throw new android.database.SQLException ("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException ("Unknown uri: " + uri);
        }
        getContext ().getContentResolver ().notifyChange (uri, null);
        return returnUri;
    }

    @Override public int delete (Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase ();
        final int match = sUriMatcher.match (uri);
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) {
            selection = "1";
        }
        switch (match) {
            case MOVIE:
                rowsDeleted = db.delete (MovieContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case FAVOURITE_MOVIE:
                rowsDeleted = db.delete (MovieContract.FavouriteMovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException ("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext ().getContentResolver ().notifyChange (uri, null);
        }
        return rowsDeleted;
    }

    /*
        private void normalizeDate (ContentValues values) {
            // normalize the date value
            if (values.containsKey (WeatherContract.WeatherEntry.COLUMN_DATE)) {
                long dateValue = values.getAsLong (WeatherContract.WeatherEntry.COLUMN_DATE);
                values.put (WeatherContract.WeatherEntry.COLUMN_DATE, WeatherContract.normalizeDate (dateValue));
            }
        }
    */
    @Override public int update (Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase ();
        final int match = sUriMatcher.match (uri);
        int rowsUpdated;

        switch (match) {
            case MOVIE:
                rowsUpdated = db.update (MovieContract.MovieEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case FAVOURITE_MOVIE:
                rowsUpdated = db.update (MovieContract.FavouriteMovieEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException ("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext ().getContentResolver ().notifyChange (uri, null);
        }
        return rowsUpdated;
    }

    private void normalizeDate (ContentValues values) {
        // normalize the date value
        if (values.containsKey (MovieContract.MovieEntry.COLUMN_RELEASE_DATE)) {
            long dateValue = values.getAsLong (MovieContract.MovieEntry.COLUMN_RELEASE_DATE);
            values.put (MovieContract.MovieEntry.COLUMN_RELEASE_DATE, MovieContract.normalizeDate (dateValue));
        }
        if (values.containsKey (MovieContract.FavouriteMovieEntry.COLUMN_ADDED_TO_FAVMOVIES_DATE)) {
            long dateValue = values.getAsLong (MovieContract.FavouriteMovieEntry.COLUMN_ADDED_TO_FAVMOVIES_DATE);
            values.put (MovieContract.FavouriteMovieEntry.COLUMN_ADDED_TO_FAVMOVIES_DATE, MovieContract.normalizeDate (dateValue));
        }
    }

    @Override public int bulkInsert (Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase ();
        final int match = sUriMatcher.match (uri);
        switch (match) {
            case MOVIE:
                db.beginTransaction ();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        normalizeDate (value);
                        long _id = db.insert (MovieContract.MovieEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful ();
                }
                finally {
                    db.endTransaction ();
                }
                getContext ().getContentResolver ().notifyChange (uri, null);
                return returnCount;
            default:
                return super.bulkInsert (uri, values);
        }
    }

    /* You do not need to call this method. This is a method specifically to assist the testing
     framework in running smoothly. You can read more at:
     http://developer.android.com/reference/android/content/ContentProvider.html#shutdown()*/
    @Override @TargetApi (11) public void shutdown () {
        mOpenHelper.close ();
        super.shutdown ();
    }
}
