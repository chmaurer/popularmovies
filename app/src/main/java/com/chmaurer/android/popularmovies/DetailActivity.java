package com.chmaurer.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Christian on 11.10.2015.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);


        return super.onCreateOptionsMenu(menu); //taken from http://stackoverflow.com/questions/9739498/android-action-bar-not-showing-overflow

        //return true;
    }

    public static class PlaceholderFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra(Intent.EXTRA_RETURN_RESULT)) {
                Movie movie = intent.getParcelableExtra(Intent.EXTRA_RETURN_RESULT);
                TextView textViewMovieDetailTitle = (TextView) rootView.findViewById(R.id.movieDetailTitle);
                textViewMovieDetailTitle.setText(movie.getOriginal_title());
                TextView textViewMovieDetailRating = (TextView) rootView.findViewById(R.id.movieDetailRating);
                textViewMovieDetailRating.setText(movie.getPopularity());
                TextView textViewMovieDetailDescription = (TextView) rootView.findViewById(R.id.movieDetailDescription);
                textViewMovieDetailDescription.setText(movie.getOverview());
                TextView textViewMovieDetailReleaseDate = (TextView) rootView.findViewById(R.id.movieDetailReleaseDate);
                textViewMovieDetailReleaseDate.setText(movie.getRelease_date());
                ImageView imageViewMovieDetailImage = (ImageView) rootView.findViewById(R.id.movieDetailImage);
                ImageAdapter.fetchMovieImageForImageView(imageViewMovieDetailImage, movie);
            }

            return rootView;
        }
    }
}
