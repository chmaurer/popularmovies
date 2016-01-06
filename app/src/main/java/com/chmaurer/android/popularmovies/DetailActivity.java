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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chmaurer.android.popularmovies.data.Movie;
import com.chmaurer.android.popularmovies.data.Review;
import com.chmaurer.android.popularmovies.data.ReviewResults;
import com.chmaurer.android.popularmovies.data.Trailer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

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
            getSupportFragmentManager ().beginTransaction ().add (R.id.container, new PlaceholderFragment ()).commit ();
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
        @Override public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate (R.layout.fragment_detail, container, false);
            Intent intent = getActivity ().getIntent ();

            if (intent != null && intent.hasExtra ("Movie")) {

                Movie movie = intent.getExtras ().getParcelable ("Movie");
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
                //add the movie trailers to the scrollview
                ListView listView = (ListView) rootView.findViewById (R.id.listViewTrailers);
                //ArrayAdapter<Button> buttonAdapter = new ArrayAdapter<> (getActivity (), android.R.layout.simple_list_item_1, getListOfMovieTrailerButtons (movie, getActivity ()));
                TrailerAdapter trailerAdapter = new TrailerAdapter (getContext (), inflater, movie.getMovieTrailers ());
                listView.setLayoutParams (new LinearLayout.LayoutParams (ViewGroup.LayoutParams.FILL_PARENT, 200 * movie.getMovieTrailers ().size ()));
                listView.setAdapter (trailerAdapter);
                ListView reviewListView = (ListView) rootView.findViewById (R.id.listViewReviews);
                Retrofit retrofit = new Retrofit.Builder ().baseUrl (ApiServiceHelper.API_PREFIX).addCallAdapterFactory (RxJavaCallAdapterFactory.create ())
                                                           .addConverterFactory (GsonConverterFactory.create ()).build ();
                IReviewApiService service = retrofit.create (IReviewApiService.class);
                Call<ReviewResults> reviews = service.getReviews (movie.getId (), getResources ().getStringArray (R.array.key)[0]);
                try {
                    ReviewResults r = reviews.execute ().body ();
                    List<Review> reviewsList = Arrays.asList (r.getResults ());
                    List<String> reviewsAsString = new ArrayList<> ();
                    for (Review review : reviewsList) {
                        StringBuffer buf = new StringBuffer ();
                        buf.append (review.getContent ()).append (" [").append (review.getAuthor ()).append ("]");
                        reviewsAsString.add (buf.toString ());
                    }
                    ArrayAdapter<String> reviewAdapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_list_item_1, reviewsAsString);
                    reviewListView.setAdapter (reviewAdapter);
                }
                catch (Exception e) {

                }

                //listView.setAdapter (buttonAdapter);
              /*  for (Trailer trailer : movie.getMovieTrailers ()) {
                                       VideoView videoView = new VideoView (getActivity ());
                    TrailerAdapter.fetchMoviePreviewImageForTrailer (videoView, trailer);
                    gridView.addView (videoView, 0);
                    gridView.setAdapter (trailerAdapter);
                    gridView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                        public void onItemClick (AdapterView<?> parent, View v, int position, long id) {
                            Intent launchMoviePlayer = new Intent ();
                            launchMoviePlayer.setAction (Intent.ACTION_VIEW);
                            launchMoviePlayer.setData (((Trailer) parent.getAdapter ().getItem (position)).getTrailerUri ());
                            startActivity (launchMoviePlayer);
                        }
                    });
                }
            */
            }

            return rootView;
        }
    }
}
