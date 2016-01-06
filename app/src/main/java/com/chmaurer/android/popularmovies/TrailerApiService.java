package com.chmaurer.android.popularmovies;

import android.content.res.Resources;

import com.chmaurer.android.popularmovies.data.Movie;
import com.chmaurer.android.popularmovies.data.Trailer;
import com.chmaurer.android.popularmovies.data.TrailerResults;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 Created by Christian on 12.12.2015.
 */
public class TrailerApiService {
    private static Resources mResources;
    private ITrailerApiService service;
    private Movie mMovie;

    public TrailerApiService (Resources resources, Movie movie) {
        mResources = resources;
        Retrofit retrofit = new Retrofit.Builder ().baseUrl (ApiServiceHelper.API_PREFIX).addCallAdapterFactory (RxJavaCallAdapterFactory.create ())
                                                   .addConverterFactory (GsonConverterFactory.create ()).build ();
        service = retrofit.create (ITrailerApiService.class);
        mMovie = movie;
    }

    public List<Trailer> getTrailerList (String sort) {
        Call<TrailerResults> trailers = service.getTrailers (mMovie.getId (), mResources.getStringArray (R.array.key)[0]);
        try {
            TrailerResults r = trailers.execute ().body ();
            return Arrays.asList (r.getResults ());
        }
        catch (IOException e) {
            return new ArrayList<> ();
        }
    }

}