package com.singletonbase.randy.popular_movies.Moview_Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.singletonbase.randy.popular_movies.Model_Data.Review;
import com.singletonbase.randy.popular_movies.Model_Data.Trailer;
import com.singletonbase.randy.popular_movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by randy on 30/05/17.
 */

public class TrailerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Trailer trailer = new Trailer();
    private List<Trailer> listTrailer;

    public TrailerAdapter (Context context, List<Trailer> listTrailer){
        this.context = context;
        this.listTrailer = listTrailer;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext(){
        return context;
    }

    public void add(Trailer trailer1){
        synchronized (trailer){
            listTrailer.add(trailer1);
        }
        notifyDataSetChanged();

    }

    public void clear(){
        synchronized (trailer){
            listTrailer.clear();
        }
        //notifyDataSetChanged();
    }

    public static class ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.image_trailer);
            textView = (TextView) view.findViewById(R.id.nama_trailer);
        }
    }
    @Override
    public int getCount() {
        return listTrailer.size();
    }

    @Override
    public Trailer getItem(int position) {
        return listTrailer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view==null){
            view = layoutInflater.inflate(R.layout.item_moview_trailer,parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Trailer trailer = getItem(position);
        viewHolder = (ViewHolder) view.getTag();

        String Video_URL = "http://img.youtube.com/vi/" + trailer.getKey() + "/0.jpg";
        Picasso.with(getContext()).load(Video_URL).into(viewHolder.imageView);
        viewHolder.textView.setText(trailer.getName());
        return view;
    }
}
