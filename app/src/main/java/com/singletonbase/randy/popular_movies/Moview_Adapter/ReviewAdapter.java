package com.singletonbase.randy.popular_movies.Moview_Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.singletonbase.randy.popular_movies.Model_Data.Review;
import com.singletonbase.randy.popular_movies.R;

import java.util.List;

/**
 * Created by randy on 30/05/17.
 */

public class ReviewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Review review =  new Review();
    private List<Review> listReview;

    public ReviewAdapter(Context context, List<Review> listReview){
        this.context = context;
        this.listReview = listReview;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext(){
        return context;
    }

    public void setData(List<Review> dataReview){
        clear();
        for (Review item : dataReview){
            add(item);
        }
    }

    public void clear(){
        synchronized (review){
            listReview.clear();
        }
        notifyDataSetChanged();
    }

    public void add(Review review1){
        synchronized (review){
            listReview.add(review1);
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder{
        public TextView Author;
        public TextView Review;

        public ViewHolder (View view){
            Author = (TextView) view.findViewById(R.id.Author_review);
            Review = (TextView) view.findViewById(R.id.Isi_review);

        }
    }

    @Override
    public int getCount() {
        return listReview.size();
    }

    @Override
    public Review getItem(int position) {
        return listReview.get(position);
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
            view = layoutInflater.inflate(R.layout.item_moview_review,parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Review review = getItem(position);
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.Author.setText(review.getAuthor());
        viewHolder.Review.setText(Html.fromHtml(review.getIsi()));

        return view;
    }
}
