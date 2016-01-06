package com.chmaurer.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 Created by Christian on 10.10.2015.
 */
public class Movie_old implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator () {
        public Movie_old createFromParcel (Parcel in) {
            return new Movie_old (in);
        }

        public Movie_old[] newArray (int size) {
            return new Movie_old[size];
        }
    };

    private String original_title;
    private String overview;
    private String poster_path;
    private String vote_average;
    private String release_date;
    private String popularity;


    // Parcelling part
    public Movie_old (Parcel in) {
        String[] data = new String[6];
        in.readStringArray (data);
        this.original_title = data[0];
        this.overview = data[1];
        this.poster_path = data[2];
        this.vote_average = data[3];
        this.release_date = data[4];
        this.popularity = data[5];
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

    @Override public String toString () {
        return this.getOriginal_title ();
    }

    @Override public int describeContents () {
        return 0;
    }

    @Override public void writeToParcel (Parcel dest, int flags) {
        dest.writeStringArray (new String[]{this.original_title, this.overview, this.poster_path, this.vote_average, this.release_date, this.popularity});
    }
}
