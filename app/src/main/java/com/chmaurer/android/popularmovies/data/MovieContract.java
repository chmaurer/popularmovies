package com.chmaurer.android.popularmovies.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 Created by Christian on 12.12.2015.
 */
public class MovieContract {

    public static final String CONTENT_AUTHORITY = "com.chmaurer.android.popularmovies.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse ("content://" + CONTENT_AUTHORITY);
    public static final String PATH_VIDEOS = "videos";
    public static final String PATH_REVIEWS = "reviews";
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_FAVOURITE_MOVIES = "favmovies";

    public static long normalizeDate (long startDate) {
        Time time = new Time ();
        time.set (startDate);
        int julianDay = Time.getJulianDay (startDate, time.gmtoff);
        return time.setJulianDay (julianDay);
    }

    public static final class FavouriteMovieEntry extends MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon ().appendPath (PATH_FAVOURITE_MOVIES).build ();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIES;
        public static final String TABLE_NAME = "favmovies";
        public static final String COLUMN_MOVIE_ID = "movieid";
        public static final String COLUMN_ADDED_TO_FAVMOVIES_DATE = "date";

        public static Uri buildMovieUri (long id) {
            return ContentUris.withAppendedId (CONTENT_URI, id);
        }
    }

    public static class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon ().appendPath (PATH_FAVOURITE_MOVIES).build ();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_ORIG_TITLE = "originalTitle";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_PATH = "posterPath";
        public static final String COLUMN_VOTE_AVG = "voteAvt";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_IS_FAVORITE = "favorite";


        public static Uri buildMovieUri (long id) {
            return ContentUris.withAppendedId (CONTENT_URI, id);
        }

        /*
        public static Uri buildWeatherLocation (String locationSetting) {
            return CONTENT_URI.buildUpon ().appendPath (locationSetting).build ();
        }

        public static Uri buildWeatherLocationWithStartDate (String locationSetting, long startDate) {
            long normalizedDate = normalizeDate (startDate);
            return CONTENT_URI.buildUpon ().appendPath (locationSetting).appendQueryParameter (COLUMN_DATE, Long.toString (normalizedDate)).build ();
        }

        public static Uri buildWeatherLocationWithDate (String locationSetting, long date) {
            return CONTENT_URI.buildUpon ().appendPath (locationSetting).appendPath (Long.toString (normalizeDate (date))).build ();
        }
*/
        public static String getIdFromUri (Uri uri) {
            return uri.getPathSegments ().get (1);
        }
/*
        public static long getDateFromUri (Uri uri) {
            return Long.parseLong (uri.getPathSegments ().get (2));
        }

        public static long getStartDateFromUri (Uri uri) {
            String dateString = uri.getQueryParameter (COLUMN_DATE);
            if (null != dateString && dateString.length () > 0) {
                return Long.parseLong (dateString);
            } else {
                return 0;
            }
        }*/
    }

}
