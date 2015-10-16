package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 10.10.2015.
 */
public class MovieDbService extends AsyncTask<String, Void, List<Movie>> {
    private Resources mResources;
    private String LOG_TAG = MovieDbService.class.getSimpleName();
    private ImageAdapter mAdapter;
    private Context mContext;


    public MovieDbService(ImageAdapter imageAdapter, Resources resources, Context context) {
        mAdapter = imageAdapter;
        mResources = resources;
        mContext = context;
    }

    protected void onPostExecute(List<Movie> movies) {
        mAdapter.clearItems();
        if (CollectionUtils.isEmpty(movies)) {
            return;
        }

        mAdapter.addAll(movies);

    }


    private List<Movie> getMovieDataFromJson(String moviesJsonStr)
            throws JSONException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_LIST = "results";
        JSONObject forecastJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = forecastJson.getJSONArray(OWM_LIST);
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < moviesArray.length(); i++) {
            Movie movieObject = new Movie();

            JSONObject movieJSON = moviesArray.getJSONObject(i);

            for (Field f : Movie.class.getDeclaredFields()) {
                try {
                    Method method = movieObject.getClass().getMethod("set" + StringUtils.capitalize(f.getName()), String.class);
                    method.invoke(movieObject, movieJSON.getString(f.getName()));
                } catch (NoSuchMethodException ex) {
                    //do nothing as this is caused by CREATOR in Movie.java
                }
            }
            movies.add(movieObject);

        }

        return movies;
    }

    private String translateSortOrder(String sortSetting) {
        return StringUtils.replace(sortSetting, " ", "_").toLowerCase();
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String moviesString = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);


        try {
            //example of api url: https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=d0cc54e7c1edb43e6292190f48adbd4c
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https")
                    .authority("api.themoviedb.org")
                    .appendPath("3")
                    .appendPath("discover")
                    .appendPath("movie")
                    .appendQueryParameter("sort_by", translateSortOrder(prefs.getString(mContext.getString(R.string.key_sort_order), mContext.getString(R.string.default_key_sort_order))) + ".desc")
                    .appendQueryParameter("api_key", mResources.getStringArray(R.array.key)[0]);

            URL url = new URL(builder.build().toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            //getting temp taken from http://stackoverflow.com/questions/5566669/how-to-parse-a-json-object-in-android and http://developer.android.com/reference/org/json/JSONObject.html
            //help by https://jsonformatter.curiousconcept.com/
            moviesString = buffer.toString();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getMovieDataFromJson(moviesString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}