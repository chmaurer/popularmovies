package com.chmaurer.android.popularmovies;

import com.chmaurer.android.popularmovies.data.ReviewResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 Created by Christian on 06.01.2016.
 */
public interface IReviewApiService {
    @GET ("3/movie/{id}/reviews") Call<ReviewResults> getReviews (@Path ("id") String movieId, @Query ("api_key") String key);
}
