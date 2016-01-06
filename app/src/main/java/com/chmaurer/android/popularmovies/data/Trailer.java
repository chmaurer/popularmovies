package com.chmaurer.android.popularmovies.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 Created by Christian on 21.12.2015.
 */
public class Trailer implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Trailer> () {
        public Trailer createFromParcel (Parcel in) {
            return new Trailer (in);
        }

        public Trailer[] newArray (int size) {
            return new Trailer[size];
        }
    };
    private static final String PREFIX_VIDEO_WATCH = "https://www.youtube.com/watch?v=";
    private static final String PREFIX_VIDEO_SEARCH = "https://www.youtube.com/results?search_query=";
    private String id;
    private String name;
    private String site;
    private String key;

    public Trailer (Parcel in) {
        String[] data = new String[4];
        /// Trailer trailer = in.readParcelable (Trailer.class.getClassLoader ());
        in.readStringArray (data);

        this.id = data[0];
        this.name = data[1];
        this.key = data[2];
        this.site = data[3];
        //this.id = trailer.getId ();
        //this.name = trailer.getName ();
        //this.key = trailer.getKey ();
        //this.site = trailer.getSite ();
    }

    public Uri getTrailerUri () {
        if ("YouTube".equals (site)) {
            return Uri.parse (PREFIX_VIDEO_WATCH + key);
        }
        return Uri.parse (PREFIX_VIDEO_SEARCH + name);
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getKey () {
        return key;
    }

    public void setKey (String key) {
        this.key = key;
    }

    public String getSite () {
        return site;
    }

    public void setSite (String site) {
        this.site = site;
    }

    @Override public int describeContents () {
        return 0;
    }

    @Override public void writeToParcel (Parcel dest, int flags) {
        dest.writeStringArray (new String[]{this.getId (), this.getName (), this.getKey (), this.getSite ()});
    }
}
