package com.singletonbase.randy.popular_movies;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ShareActionProvider;
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

import com.singletonbase.randy.popular_movies.LinearListView.LinearListView;
import com.singletonbase.randy.popular_movies.Model_Data.Moview;
import com.singletonbase.randy.popular_movies.Model_Data.Trailer;
import com.singletonbase.randy.popular_movies.Moview_Adapter.ReviewAdapter;
import com.singletonbase.randy.popular_movies.Moview_Adapter.TrailerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Detail_Fragment extends Fragment {

    public static final String TAG = Detail_Fragment.class.getSimpleName();
    public static final String DETAIL_MOVIEW = "DETAIL_MOVIE";
    private Moview moview;
    private ImageView imageView;

    private TextView titleView;
    private TextView overviewView;
    private TextView dateView;
    private TextView voteView;

    private LinearListView TrailerListView;
    private LinearListView ReviewListView;

    private CardView ReviewCardView;
    private CardView TrailerCardView;

    private TrailerAdapter TrailerAdapter;
    private ReviewAdapter ReviewAdapter;

    private ScrollView scrollDetail;
    private Toast toast;
    private ShareActionProvider shareActionProvider;

    private Trailer trailer;

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
        if (moview==null){
            inflater.inflate(R.menu.menu_fragment_detail,menu);

            final MenuItem actionFavorite = menu.findItem(R.id.favorite);
            MenuItem actionShare = menu.findItem(R.id.share);

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    return ;
                }
            }
        }
        //super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_, container, false);
    }

}
