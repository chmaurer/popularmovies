package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 Created by Christian on 21.12.2015.
 */
public class ReviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private List<String> mReviews = new ArrayList<> ();

    public ReviewAdapter (Context context, LayoutInflater c) {
        super ();
        inflater = c;
        mContext = context;
    }

    public List<String> getReviews () {
        return mReviews;
    }

    public int getCount () {
        return mReviews.size ();
    }

    public Object getItem (int position) {
        return mReviews.get (position);
    }

    public long getItemId (int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView (final int position, View convertView, ViewGroup parent) {
        View imageView;
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = inflater.inflate (R.layout.list_item_review, parent, false);
        } else {
            imageView = convertView;
        }
        textView = (TextView) imageView.findViewById (R.id.userReview);
        textView.setText (mReviews.get (position));
        textView.setHeight (100);
        return imageView;
    }
}
