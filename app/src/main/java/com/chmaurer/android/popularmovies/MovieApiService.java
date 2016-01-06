package com.chmaurer.android.popularmovies;

import android.content.res.Resources;

import com.chmaurer.android.popularmovies.data.Movie;
import com.chmaurer.android.popularmovies.data.MovieResults;

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
public class MovieApiService {
    private static Resources mResources;
    private IMovieApiService service;
    private Retrofit retrofit;

    public MovieApiService (Resources resources) {
        mResources = resources;
        retrofit = new Retrofit.Builder ().baseUrl (ApiServiceHelper.API_PREFIX).addCallAdapterFactory (RxJavaCallAdapterFactory.create ()).addConverterFactory (GsonConverterFactory.create ())
                                          .build ();
        service = retrofit.create (IMovieApiService.class);

    }

    public List<Movie> getMovieList (String sort) {
        Call<MovieResults> movies = service.getMovieList (sort, mResources.getStringArray (R.array.key)[0]);
        try {
            MovieResults r = movies.execute ().body ();
            List<Movie> moviesList = Arrays.asList (r.getResults ());
            for (Movie m : moviesList) {
                TrailerApiService trailerService = new TrailerApiService (mResources, m);
                m.setMovieTrailers (trailerService.getTrailerList (sort));
            }
            return moviesList;
        }
        catch (Exception e) {
            return new ArrayList<> ();
        }
    }

}