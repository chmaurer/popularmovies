package com.chmaurer.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 Created by Christian on 21.12.2015.
 */
public class Movie implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Movie> () {
        public Movie createFromParcel (Parcel in) {
            return new Movie (in);
        }

        public Movie[] newArray (int size) {
            return new Movie[size];
        }
    };
    private String id;
    private String original_title;
    private String overview;
    private String poster_path;
    private String vote_average;
    private String release_date;
    private String popularity;
    private List<Trailer> movieTrailers;


    // Parcelling part
    public Movie (Parcel in) {
        String[] data = new String[7];
        in.readStringArray (data);
        //  Movie m = in.readParcelable (Movie.class.getClassLoader ());
        this.original_title = data[0];
        this.overview = data[1];
        this.poster_path = data[2];
        this.vote_average = data[3];
        this.release_date = data[4];
        this.popularity = data[5];
        this.id = data[6];
       /*
        this.id = m.getId ();
        this.original_title = m.getOriginal_title ();
        this.overview = m.getOverview ();
        this.poster_path = m.getPoster_path ();
        this.vote_average = m.getVote_average ();
        this.release_date = m.getRelease_date ();
        this.popularity = m.getPopularity ();*/
        List<Trailer> trailers = new ArrayList<> ();
        in.readList (trailers, Trailer.class.getClassLoader ());
        this.movieTrailers = trailers;

    }

    public String getOriginal_title () {
        return original_title;
    }

    public void setOriginal_title (String original_title) {
        this.original_title = original_title;
    }

    public String getOverview () {
        return overview;
    }

    public void setOverview (String overview) {
        this.overview = overview;
    }

    public String getPoster_path () {
        return poster_path;
    }

    public void setPoster_path (String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVote_average () {
        return vote_average;
    }

    public void setVote_average (String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date () {
        return release_date;
    }

    public void setRelease_date (String release_date) {
        this.release_date = release_date;
    }

    public String getPopularity () {
        return popularity;
    }

    public void setPopularity (String popularity) {
        this.popularity = popularity;
    }

    public List<Trailer> getMovieTrailers () {
        return movieTrailers;
    }

    public void setMovieTrailers (List<Trailer> movieTrailers) {
        this.movieTrailers = movieTrailers;
    }

    @Override public String toString () {
        return this.getOriginal_title ();
    }

    @Override public int describeContents () {
        return 0;
    }


    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    @Override public void writeToParcel (Parcel dest, int flags) {
        dest.writeStringArray (new String[]{this.original_title, this.overview, this.poster_path, this.vote_average, this.release_date, this.popularity, this.id});
        dest.writeList (this.getMovieTrailers ());
    }
}

