package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.chmaurer.android.popularmovies.data.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 Created by Christian on 21.12.2015.
 */
public class TrailerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<Trailer> trailers = new ArrayList<> ();

    public TrailerAdapter (Context context, LayoutInflater c, List<Trailer> trailersArg) {
        super ();
        inflater = c;
        trailers = trailersArg;
        mContext = context;
        //super();
    }

    public void fetchMoviePreviewImageForTrailer (ImageButton videoView, Trailer trailer) {
        Picasso.with (videoView.getContext ()).load ("http://img.youtube.com/vi/" + trailer.getKey () + "/default.jpg").into (videoView);
    }

    public int getCount () {
        return trailers.size ();
    }

    public Object getItem (int position) {
        return trailers.get (position);
    }

    public long getItemId (int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView (final int position, View convertView, ViewGroup parent) {
        View imageView;
        ImageButton button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = inflater.inflate (R.layout.list_item_video, parent, false);
        } else {
            imageView = convertView;
        }
        button = (ImageButton) imageView.findViewById (R.id.videoView);
        // vv.setLayoutParams (new GridView.LayoutParams (85, 85));
        //  vv.setPadding (8, 8, 8, 8);
        //  vv.setVideoURI (trailers.get (position).getTrailerUri ());
        button.setOnClickListener (new View.OnClickListener () {
            public void onClick (View v) {
                mContext.startActivity (new Intent (Intent.ACTION_VIEW, trailers.get (position).getTrailerUri ()));
            }
        });
        // button.setImageBitmap (ThumbnailUtils.createVideoThumbnail (trailers.get (position).getTrailerUri ().getEncodedPath (), MediaStore.Images.Thumbnails.MINI_KIND));
        fetchMoviePreviewImageForTrailer (button, trailers.get (position));
        return imageView;
    }
}
