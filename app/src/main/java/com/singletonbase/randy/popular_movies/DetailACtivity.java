package com.singletonbase.randy.popular_movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailACtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);

        if (savedInstanceState == null){
            Bundle args = new Bundle();
            args.putParcelable(Detail_Fragment.DETAIL_MOVIEW,getIntent().getParcelableExtra(Detail_Fragment.DETAIL_MOVIEW));

            Detail_Fragment fragment = new Detail_Fragment();
            fragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detail_moview_container,fragment)
                    .commit();


        }
    }
}
