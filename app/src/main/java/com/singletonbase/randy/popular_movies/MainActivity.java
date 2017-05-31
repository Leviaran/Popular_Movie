package com.singletonbase.randy.popular_movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.singletonbase.randy.popular_movies.Model_Data.Moview;

public class MainActivity extends AppCompatActivity implements Main_Fragment.Callback {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.detail_moview_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_moview_container, new Detail_Fragment(),
                                Detail_Fragment.TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }


    @Override
    public void onItemSelected(Moview movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(Detail_Fragment.DETAIL_MOVIEW, movie);

            Detail_Fragment fragment = new Detail_Fragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_moview_container, fragment, Detail_Fragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailACtivity.class)
                    .putExtra(Detail_Fragment.DETAIL_MOVIEW, movie);
            startActivity(intent);
        }
    }
}
