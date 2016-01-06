package com.chmaurer.android.popularmovies;

import com.chmaurer.android.popularmovies.data.TrailerResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 Created by Christian on 21.12.2015.
 */
public interface ITrailerApiService {
    @GET ("3/movie/{id}/videos") Call<TrailerResults> getTrailers (@Path ("id") String movieId, @Query ("api_key") String key);
}
