package com.chmaurer.android.popularmovies;

import com.chmaurer.android.popularmovies.data.MovieResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 Created by Christian on 12.12.2015.
 */
public interface IMovieApiService {
    @GET ("3/discover/movie") Call<MovieResults> getMovieList (@Query ("sort") String sort, @Query ("api_key") String key);

    @GET ("3/discover/movie/{id}") Call<MovieResults> getMovie (@Path ("id") int groupId, @Query ("sort") String sort, @Query ("api_key") String key);
}
