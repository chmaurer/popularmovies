package com.chmaurer.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivityFragment extends Fragment {
    ImageAdapter imageAdapter;

    @Override public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_main, container, false);
    }

    @Override public void onStart () {
        super.onStart ();
        GridView gridview = (GridView) getActivity ().findViewById (R.id.gridview);
        imageAdapter = new ImageAdapter (getActivity ().getApplicationContext ());
        (new MovieDbService (imageAdapter, getResources (), getActivity ().getApplicationContext ())).execute ();
        gridview.setAdapter (imageAdapter);
        gridview.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            public void onItemClick (AdapterView<?> parent, View v, int position, long id) {
                Intent detailIntent = new Intent (getActivity ().getApplicationContext (), DetailActivity.class);
                detailIntent.putExtra ("Movie", (Parcelable) imageAdapter.getItem (position));
                startActivity (detailIntent);
            }
        });
    }
}
