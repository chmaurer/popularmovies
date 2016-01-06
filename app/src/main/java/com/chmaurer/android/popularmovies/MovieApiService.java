package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.content.res.Resources;

import com.chmaurer.android.popularmovies.data.Movie;
import com.chmaurer.android.popularmovies.data.MovieProvider;
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
    private Context context;

    public MovieApiService (Resources resources, Context c) {
        mResources = resources;
        retrofit = new Retrofit.Builder ().baseUrl (ApiServiceHelper.API_PREFIX).addCallAdapterFactory (RxJavaCallAdapterFactory.create ()).addConverterFactory (GsonConverterFactory.create ())
                                          .build ();
        service = retrofit.create (IMovieApiService.class);
        context = c;

    }

    public List<Movie> getMovieList (String sort) {
        Call<MovieResults> movies;
        if (sort.equalsIgnoreCase (mResources.getString (R.string.sortOrderFavorites))) {
            movies = service.getMovieList (mResources.getString (R.string.default_key_sort_order), mResources.getStringArray (R.array.key)[0]);
        } else {
            movies = service.getMovieList (sort, mResources.getStringArray (R.array.key)[0]);
        }
        try {
            MovieResults r = movies.execute ().body ();
            List<Movie> moviesList = Arrays.asList (r.getResults ());
            MovieProvider movieProvider = new MovieProvider (context);
            List<Movie> retMovies = new ArrayList<> ();
            for (Movie m : moviesList) {
                if (sort.equalsIgnoreCase (mResources.getString (R.string.sortOrderFavorites)) && !movieProvider.isFavorite (m)) {
                    continue;
                }
                TrailerApiService trailerService = new TrailerApiService (mResources, m);
                m.setMovieTrailers (trailerService.getTrailerList (sort));
                retMovies.add (m);
            }
            return retMovies;
        }
        catch (Exception e) {
            return new ArrayList<> ();
        }
    }

}