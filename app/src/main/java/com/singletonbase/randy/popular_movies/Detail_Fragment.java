package com.singletonbase.randy.popular_movies;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ShareActionProvider;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.linearlistview.LinearListView;
import com.singletonbase.randy.popular_movies.Data.MovieList;
import com.singletonbase.randy.popular_movies.Model_Data.Moview;
import com.singletonbase.randy.popular_movies.Model_Data.Review;
import com.singletonbase.randy.popular_movies.Model_Data.Trailer;
import com.singletonbase.randy.popular_movies.Moview_Adapter.ReviewAdapter;
import com.singletonbase.randy.popular_movies.Moview_Adapter.TrailerAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */



public class Detail_Fragment extends Fragment {

    public static final String TAG = Detail_Fragment.class.getSimpleName();
    public static final String DETAIL_MOVIEW = "DETAIL_MOVIE";
    private Moview moview;
    private ImageView imageView;
    private ImageView imageView2;

    private Typeface typeface;

    private TextView titleView;
    private TextView overviewView;
    private TextView dateView;
    private TextView voteView;
    private TextView popularity;

    private LinearListView TrailerListView;
    private LinearListView ReviewListView;

    private CardView ReviewCardView;
    private CardView TrailerCardView;

    private TrailerAdapter TrailerAdapter;
    private ReviewAdapter ReviewAdapter;

    private ScrollView scrollDetail;
    private Toast toast;
    private ShareActionProvider shareActionProvider;

    private Trailer trailer1;



    public Detail_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (moview!=null){
            inflater.inflate(R.menu.menu_fragment_detail,menu);

            final MenuItem actionFavorite = menu.findItem(R.id.favorite);
            MenuItem actionShare = menu.findItem(R.id.share);

            new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... params) {
                    return Utilitas.isFavorite(getActivity(),moview.getId());
                    //return Utilitas.isFavorite(getActivity(),moview.getId());
                }

                @Override
                protected void onPostExecute(Integer integer) {
                    //super.onPostExecute(integer);
                    actionFavorite.setIcon(integer == 1?
                    R.mipmap.ic_star_on :
                    R.mipmap.ic_star);
                }
            }.execute();

            shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(actionShare);

            if(trailer1 != null){
                shareActionProvider.setShareIntent(createShareMovieIntent());
            }
        }
        //super.onCreateOptionsMenu(menu, inflater);
    }

    private Intent createShareMovieIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, moview.getTitle() + " " +
                "http://www.youtube.com/watch?v=" + trailer1.getKey());
        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.favorite:
                if (moview != null) {
                    new AsyncTask<Void, Void, Integer>() {

                        @Override
                        protected Integer doInBackground(Void... params) {
                            return Utilitas.isFavorite(getActivity(),moview.getId());
                        }

                        @Override
                        protected void onPostExecute(Integer isFavorited) {
                            if (isFavorited == 1) {

                                new AsyncTask<Void, Void, Integer>() {
                                    @Override
                                    protected Integer doInBackground(Void... params) {
                                        return getActivity().getContentResolver().delete(
                                                MovieList.MovieEntry.CONTENT_URI,
                                                MovieList.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                                                new String[]{moview.getId()}
                                        );
                                    }

                                    @Override
                                    protected void onPostExecute(Integer rowsDeleted) {
                                        item.setIcon(R.mipmap.ic_star);
                                        if (toast != null) {
                                            toast.cancel();
                                        }
                                        toast = Toast.makeText(getActivity(), getString(R.string.removed_from_favorites), Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }.execute();
                            }
                            else {
                                new AsyncTask<Void, Void, Uri>() {
                                    @Override
                                    protected Uri doInBackground(Void... params) {
                                        ContentValues values = new ContentValues();

                                        values.put(MovieList.MovieEntry.COLUMN_MOVIE_ID, moview.getId());
                                        values.put(MovieList.MovieEntry.COLUMN_TITLE, moview.getTitle());
                                        values.put(MovieList.MovieEntry.COLUMN_IMAGE, moview.getImage());
                                        values.put(MovieList.MovieEntry.COLUMN_IMAGE2, moview.getImage2());
                                        values.put(MovieList.MovieEntry.COLUMN_OVERVIEW, moview.getOverview());
                                        values.put(MovieList.MovieEntry.COLUMN_RATING, moview.getRating());
                                        values.put(MovieList.MovieEntry.COLUMN_DATE, moview.getDate());
                                        values.put(MovieList.MovieEntry.COLUMN_POPULARITY,moview.getPopularity());

                                        return getActivity().getContentResolver().insert(MovieList.MovieEntry.CONTENT_URI,
                                                values);
                                    }

                                    @Override
                                    protected void onPostExecute(Uri returnUri) {
                                        item.setIcon(R.mipmap.ic_star_on);
                                        if (toast != null) {
                                            toast.cancel();
                                        }
                                        toast = Toast.makeText(getActivity(), getString(R.string.added_to_favorites), Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }.execute();
                            }
                        }
                    }.execute();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle arguments = getArguments();
        if (arguments != null) {
            moview = arguments.getParcelable(Detail_Fragment.DETAIL_MOVIEW);
        }

        View rootView = inflater.inflate(R.layout.fragment_detail_, container, false);

        scrollDetail = (ScrollView) rootView.findViewById(R.id.detail_grid_movie);

        if (moview != null) {
            scrollDetail.setVisibility(View.VISIBLE);
        } else {
            scrollDetail.setVisibility(View.INVISIBLE);
        }

        imageView = (ImageView) rootView.findViewById(R.id.image_detail);
        imageView2 = (ImageView) rootView.findViewById(R.id.img_thumbnail);

        typeface = Typeface.createFromAsset(getContext().getAssets(),"roboto.ttf");

        titleView = (TextView) rootView.findViewById(R.id.title_detail);
        overviewView = (TextView) rootView.findViewById(R.id.Detail_OverView);

        overviewView.setTypeface(typeface);

        dateView = (TextView) rootView.findViewById(R.id.Date_detail);
        voteView = (TextView) rootView.findViewById(R.id.Vote_detail);
        popularity = (TextView) rootView.findViewById(R.id.popularity);

        TrailerListView = (LinearListView) rootView.findViewById(R.id.detail_trailers);
        ReviewListView = (LinearListView) rootView.findViewById(R.id.detail_reviews);

        ReviewCardView = (CardView) rootView.findViewById(R.id.detail_reviews_cardview);
        TrailerCardView = (CardView) rootView.findViewById(R.id.detail_trailers_cardview);
        TrailerCardView.setBackgroundColor(Color.parseColor("#5C3F51B5"));

        TrailerAdapter = new TrailerAdapter(getActivity(), new ArrayList<Trailer>());
        TrailerListView.setAdapter(TrailerAdapter);

        TrailerListView.setOnItemClickListener(new LinearListView.OnItemClickListener() {
            @Override
            public void onItemClick(LinearListView linearListView, View view,
                                    int position, long id) {
                Trailer trailer = TrailerAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
                startActivity(intent);
            }
        });

        ReviewAdapter = new ReviewAdapter(getActivity(), new ArrayList<Review>());
        ReviewListView.setAdapter(ReviewAdapter);

        if (moview != null) {

            String image_url = Utilitas.BuildeImageURL(342, moview.getImage2());
            String image = Utilitas.BuildeImageURL(342, moview.getImage());

            Glide.with(this).load(image_url).into(imageView);
            Glide.with(this).load(image).into(imageView2);

            titleView.setText(moview.getTitle());
            overviewView.setText(moview.getOverview());
            popularity.setText(moview.getPopularity());

            String movie_date = moview.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String date = DateUtils.formatDateTime(getActivity(),
                        formatter.parse(movie_date).getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
                dateView.setText(date);
            } catch (ParseException e) {
                Log.i("Info",e.getMessage());
            }

            voteView.setText(Integer.toString(moview.getRating())+ "/10");
        }
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (moview != null) {
            new FetchTrailersTask().execute(moview.getId());
            new FetchReviewsTask().execute(moview.getId());
        }
    }

    public class FetchTrailersTask extends AsyncTask<String, Void, List<Trailer>> {


        private final String LOG_TAG = FetchTrailersTask.class.getSimpleName();

        private List<Trailer> getTrailersDataFromJson(String jsonStr) throws JSONException {
            JSONObject trailerJson = new JSONObject(jsonStr);
            JSONArray trailerArray = trailerJson.getJSONArray("results");


            List<Trailer> results = new ArrayList<>();

            for(int i = 0; i < trailerArray.length(); i++) {
                JSONObject trailer = trailerArray.getJSONObject(i);
                if (trailer.getString("site").contentEquals("YouTube")) {
                    Trailer trailerModel = new Trailer(trailer);
                    results.add(trailerModel);
                }
            }

            return results;
        }

        @Override
        protected List<Trailer> doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String jsonStr = null;

            try {
                final String BASE_URL = "http://api.themoviedb.org/3/movie/" + params[0] + "/videos";
                final String API_KEY_PARAM = "api_key";


                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(API_KEY_PARAM, "6abab54128d36173f70cbd9f9a415e33")
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                jsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getTrailersDataFromJson(jsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Trailer> trailers) {
            if (trailers != null) {
                if (trailers.size() > 0) {
                    TrailerCardView.setVisibility(View.VISIBLE);
                    if (TrailerAdapter != null) {
                        TrailerAdapter.clear();
                        for (Trailer trailer : trailers) {
                            TrailerAdapter.add(trailer);
                        }
                    }

                    trailer1 = trailers.get(0);
                    if (shareActionProvider != null) {
                        shareActionProvider.setShareIntent(createShareMovieIntent());
                    }
                }
            }
        }
    }

    public class FetchReviewsTask extends AsyncTask<String, Void, List<Review>> {

        private final String LOG_TAG = FetchReviewsTask.class.getSimpleName();

        private List<Review> getReviewsDataFromJson(String jsonStr) throws JSONException {
            JSONObject reviewJson = new JSONObject(jsonStr);
            JSONArray reviewArray = reviewJson.getJSONArray("results");


            List<Review> results = new ArrayList<>();

            for(int i = 0; i < reviewArray.length(); i++) {
                JSONObject review = reviewArray.getJSONObject(i);
                results.add(new Review(review));
            }

            return results;
        }

        @Override
        protected List<Review> doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String jsonStr = null;

            try {
                final String BASE_URL = "http://api.themoviedb.org/3/movie/" + params[0] + "/reviews";
                final String API_KEY_PARAM = "api_key";
                //Log.i("URL",BASE_URL);

                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(API_KEY_PARAM, "6abab54128d36173f70cbd9f9a415e33")
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                jsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getReviewsDataFromJson(jsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(List<Review> reviews) {
            if (reviews != null) {
                if (reviews.size() > 0) {
                    ReviewCardView.setVisibility(View.VISIBLE);
                    if (ReviewAdapter != null) {
                        ReviewAdapter.clear();
                        for (Review review : reviews) {
                            ReviewAdapter.add(review);
                        }
                    }
                }
            }
        }
    }

    }
