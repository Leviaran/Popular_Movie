package com.singletonbase.randy.popular_movies.Moview_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.singletonbase.randy.popular_movies.Model_Data.Moview;
import com.singletonbase.randy.popular_movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by randy on 30/05/17.
 */

public class Moview_GridAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Moview moview = new Moview();

    private List<Moview> listMoview;

    public Moview_GridAdapter(Context context, List<Moview> listMoview){
        this.context = context;
        this.listMoview  = listMoview;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext(){
        return context;
    }

    public void setData(List<Moview> dataMoview){
        clear();
        for (Moview movie : dataMoview){
            add(movie);
        }
    }

    public void clear(){
        synchronized (moview){
            listMoview.clear();
        }
        notifyDataSetChanged();
    }

    public void add(Moview list1){
        synchronized (moview){
            listMoview.add(list1);
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.Grid_image_item);
            textView = (TextView) view.findViewById(R.id.Grid_title_item);
        }
    }

    @Override
    public int getCount() {
        return listMoview.size();
    }

    @Override
    public Moview getItem(int position) {
        return listMoview.get(position);
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
            view = layoutInflater.inflate(R.layout.grid_item_moview,parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Moview movie = getItem(position);
        String image_URL = "http://image.tmdb.org/t/p/w185" + movie.getImage();

        viewHolder = (ViewHolder) view.getTag();
        Picasso.with(getContext()).load(image_URL).placeholder(R.drawable.placeholder).into(viewHolder.imageView);
        viewHolder.textView.setText(movie.getTitle());
        return view;
    }
}
