package com.singletonbase.randy.popular_movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.singletonbase.randy.popular_movies.Model_Data.Moview;
import com.singletonbase.randy.popular_movies.Moview_Adapter.Moview_GridAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main_Fragment extends Fragment {

    private GridView gridView;
    private Moview_GridAdapter moview_gridAdapter;


    public Main_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_,container,false);
        gridView = (GridView) view.findViewById(R.id.Grid_view);

        Moview moview = new Moview();

        List<Moview> list = new ArrayList<>();
        list.add(moview);
        list.add(moview);
        list.add(moview);
        list.add(moview);

        moview_gridAdapter= new Moview_GridAdapter(getActivity(),list);
        gridView.setAdapter(moview_gridAdapter);

        return view;
    }

}
