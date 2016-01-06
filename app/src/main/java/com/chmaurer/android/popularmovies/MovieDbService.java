package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.chmaurer.android.popularmovies.data.Movie;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 Created by Christian on 10.10.2015.
 */
public class MovieDbService extends AsyncTask<String, Void, List<Movie>> {
    private Resources mResources;
    private String LOG_TAG = MovieDbService.class.getSimpleName ();
    private ImageAdapter mAdapter;
    private Context mContext;

    public MovieDbService (ImageAdapter imageAdapter, Resources resources, Context context) {
        mAdapter = imageAdapter;
        mResources = resources;
        mContext = context;
    }

    protected void onPostExecute (List<Movie> movies) {
        mAdapter.clearItems ();
        if (CollectionUtils.isEmpty (movies)) {
            return;
        }
        mAdapter.addAll (movies);
    }

    private String translateSortOrder (String sortSetting) {
        return StringUtils.replace (sortSetting, " ", "_").toLowerCase ();
    }

    @Override protected List<Movie> doInBackground (String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        MovieApiService apiService = new MovieApiService (mResources);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (mContext);
        return apiService.getMovieList (translateSortOrder (prefs.getString (mContext.getString (R.string.key_sort_order), mContext.getString (R.string.default_key_sort_order))));

    }
}