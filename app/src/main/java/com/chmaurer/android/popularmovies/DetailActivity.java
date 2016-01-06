package com.chmaurer.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.chmaurer.android.popularmovies.data.Movie;
import com.chmaurer.android.popularmovies.data.MovieProvider;
import com.chmaurer.android.popularmovies.data.Trailer;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 Created by Christian on 11.10.2015.
 */
public class DetailActivity extends AppCompatActivity {

    public static List<Button> getListOfMovieTrailerButtons (Movie m, Context c) {
        List<Button> buttons = new ArrayList<> ();
        for (Trailer t : m.getMovieTrailers ()) {
            Button b = new Button (c);
            b.setText (t.getName ());
            buttons.add (b);
        }
        return buttons;
    }

    @Override protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle ();
            arguments.putParcelable (PlaceholderFragment.DETAIL_URI, getIntent ().getData ());
            PlaceholderFragment fragment = new PlaceholderFragment ();
            fragment.setArguments (arguments);
            getSupportFragmentManager ().beginTransaction ().add (R.id.movieDetailContainer, fragment).commit ();
        }
    }

    @Override public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity (new Intent (this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @Override public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.settings, menu);
        return super.onCreateOptionsMenu (menu);
    }

    public static class PlaceholderFragment extends Fragment {
        static final String DETAIL_URI = "Movie";

        @Override public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Movie mov = null;
            Bundle arguments = getArguments ();
            if (arguments != null && arguments.getParcelable (DETAIL_URI) != null) {
                mov = arguments.getParcelable (DETAIL_URI);
            } else {
                Intent intent = getActivity ().getIntent ();
                if (intent != null && intent.hasExtra ("Movie")) {
                    mov = intent.getExtras ().getParcelable ("Movie");
                }
            }
            View rootView = inflater.inflate (R.layout.fragment_detail, container, false);
            final Movie movie = mov;
            if (movie != null) {
                TextView textViewMovieDetailTitle = (TextView) rootView.findViewById (R.id.movieDetailTitle);
                textViewMovieDetailTitle.setText (movie.getOriginal_title ());
                TextView textViewMovieDetailRating = (TextView) rootView.findViewById (R.id.movieDetailRating);
                textViewMovieDetailRating.setText (movie.getPopularity ());
                TextView textViewMovieDetailDescription = (TextView) rootView.findViewById (R.id.movieDetailDescription);
                textViewMovieDetailDescription.setText (movie.getOverview ());
                TextView textViewMovieDetailReleaseDate = (TextView) rootView.findViewById (R.id.movieDetailReleaseDate);
                textViewMovieDetailReleaseDate.setText (movie.getRelease_date ());
                ImageView imageViewMovieDetailImage = (ImageView) rootView.findViewById (R.id.movieDetailImage);
                ImageAdapter.fetchMovieImageForImageView (imageViewMovieDetailImage, movie);
                ListView listView = (ListView) rootView.findViewById (R.id.listViewTrailers);
                TrailerAdapter trailerAdapter = new TrailerAdapter (getContext (), inflater, movie.getMovieTrailers ());
                listView.setLayoutParams (new LinearLayout.LayoutParams (ViewGroup.LayoutParams.FILL_PARENT, 100 * CollectionUtils.size (movie.getMovieTrailers ())));
                listView.setAdapter (trailerAdapter);
                TextView reviewTextField = (TextView) rootView.findViewById (R.id.movieUserReview);
                // ReviewAdapter reviewAdapter = new ReviewAdapter (getContext (), inflater);
                new ReviewApiService (reviewTextField, getResources (), getContext (), movie).execute ();
                ToggleButton favButton = (ToggleButton) rootView.findViewById (R.id.buttonAddAsFavourite);
                MovieProvider movieProvider = new MovieProvider (getContext ());
                favButton.setChecked (movieProvider.isFavorite (movie));
                if (favButton.isChecked ()) {
                    favButton.setText (getResources ().getString (R.string.removeFavourite));
                } else {
                    favButton.setText (getResources ().getString (R.string.addFavorite));
                }
                favButton.setTextOff (getResources ().getString (R.string.addFavorite));
                favButton.setTextOn (getResources ().getString (R.string.removeFavourite));
                favButton.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
                    public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
                        MovieProvider movieProvider = new MovieProvider (getContext ());
                        if (isChecked) {
                            movieProvider.insert (movie);
                        } else {
                            movieProvider.delete (movie);
                        }
                    }
                });
                //   if (movie.getFavoriteMovie () != null && Boolean.TRUE.equals (movie.getFavoriteMovie ())) {
                //      favButton.setChecked (movie.getFavoriteMovie ());
                // }
            }
            return rootView;
        }
    }
}
