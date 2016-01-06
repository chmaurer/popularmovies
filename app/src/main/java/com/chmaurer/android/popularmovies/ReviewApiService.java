package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.TextView;

import com.chmaurer.android.popularmovies.data.Movie;
import com.chmaurer.android.popularmovies.data.Review;
import com.chmaurer.android.popularmovies.data.ReviewResults;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 Created by Christian on 12.12.2015.
 */
public class ReviewApiService extends AsyncTask<String, Void, Void> {
    private static Resources mResources;
    private IReviewApiService service;
    private Retrofit retrofit;
    private TextView mAdapter;
    private Context mContext;
    private String mReviews;
    private Movie mMovie;

    public ReviewApiService (TextView textAdapter, Resources resources, Context context, Movie movie) {
        mResources = resources;
        retrofit = new Retrofit.Builder ().baseUrl (ApiServiceHelper.API_PREFIX).addCallAdapterFactory (RxJavaCallAdapterFactory.create ()).addConverterFactory (GsonConverterFactory.create ())
                                          .build ();
        service = retrofit.create (IReviewApiService.class);
        mAdapter = textAdapter;
        mContext = context;
        mMovie = movie;
    }

    @Override protected void onPostExecute (Void aVoid) {
        mAdapter.setText (StringUtils.EMPTY);
        mAdapter.setText (mReviews);
    }


    private void getReviewList (Movie movie) {
        Retrofit retrofit = new Retrofit.Builder ().baseUrl (ApiServiceHelper.API_PREFIX).addCallAdapterFactory (RxJavaCallAdapterFactory.create ())
                                                   .addConverterFactory (GsonConverterFactory.create ()).build ();
        StringBuilder reviewsAsString = new StringBuilder ();
        try {
            IReviewApiService service = retrofit.create (IReviewApiService.class);
            Call<ReviewResults> reviews = service.getReviews (movie.getId (), mResources.getStringArray (R.array.key)[0]);
            ReviewResults r = reviews.execute ().body ();
            List<Review> reviewsList = Arrays.asList (r.getResults ());
            for (Review review : reviewsList) {
                StringBuilder buf = new StringBuilder ();
                buf.append (review.getContent ()).append (" [").append (review.getAuthor ()).append ("]");
                reviewsAsString.append (buf.toString ()).append ("\n\n");
            }

        }
        catch (Exception e) {
            e.printStackTrace ();
        }
        mReviews = reviewsAsString.toString ();
    }

    @Override protected Void doInBackground (String... params) {
        getReviewList (mMovie);
        return null;
    }
}