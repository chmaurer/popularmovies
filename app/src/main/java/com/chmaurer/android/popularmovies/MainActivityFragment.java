package com.chmaurer.android.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.chmaurer.android.popularmovies.data.Movie;

public class MainActivityFragment extends Fragment {
    ImageAdapter imageAdapter;
    ViewGroup mContainer;

    @Override public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu (true);
    }

    @Override public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
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
                MainActivity mainActivity = (MainActivity) getActivity ();
                mainActivity.onMovieSelectedCallback ((Movie) imageAdapter.getItem (position));
            }
        });
    }

}
