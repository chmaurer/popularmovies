package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.chmaurer.android.popularmovies.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 Created by Christian on 10.10.2015.
 */
public class ImageAdapter extends BaseAdapter {
    private static String baseUrl = "http://image.tmdb.org/t/p/w185";
    private Context mContext;
    private List<Movie> movies = new ArrayList<> ();

    public ImageAdapter (Context c) {
        mContext = c;
        //super();
    }

    public static void fetchMovieImageForImageView (ImageView imageView, Movie movie) {
        Picasso.with (imageView.getContext ()).load (baseUrl + movie.getPoster_path ()).into (imageView);
    }

    public void clearItems () {
        movies.clear ();
    }

    public List<Movie> getMovies () {
        return movies;
    }

    public void addAll (List<Movie> m) {
        movies.addAll (m);
        notifyDataSetChanged ();
    }

    public int getCount () {
        return movies.size ();
    }

    public Object getItem (int position) {
        return movies.get (position);
    }

    public long getItemId (int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView (int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView (mContext);
            imageView.setLayoutParams (new GridView.LayoutParams (85, 85));
            imageView.setScaleType (ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding (8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        fetchMovieImageForImageView (imageView, movies.get (position));
        // Picasso.with(imageView.getContext()).load(baseUrl + movies.get(position).getPoster_path()).into(imageView);
        return imageView;
    }

}