package com.singletonbase.randy.popular_movies.Model_Data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.singletonbase.randy.popular_movies.Main_Fragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by randy on 30/05/17.
 */

public class Moview implements Parcelable {

    private String id;
    private String title;
    private String image;
    private String image2;
    private String overview;
    private String date;
    private int rating;
    private String popularity;



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getImage2() {
        return image2;
    }

    public String getOverview() {
        return overview;
    }

    public String getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public String getPopularity(){return popularity;}



    public Moview(){}

    public Moview(Cursor cursor){
        this.id=cursor.getString(Main_Fragment.COL_MOVIE_ID);
        this.title=cursor.getString(Main_Fragment.COL_TITLE);
        this.image= cursor.getString(Main_Fragment.COL_IMAGE);
        this.image2= cursor.getString(Main_Fragment.COL_IMAGE2);
        this.overview= cursor.getString(Main_Fragment.COL_OVERVIEW);
        this.date= cursor.getString(Main_Fragment.COL_DATE);
        this.rating= cursor.getInt(Main_Fragment.COL_RATING);
        this.popularity= cursor.getString(Main_Fragment.COL_POPULARITY);
    }

    public Moview(JSONObject movie) throws JSONException {
        this.id = movie.getString("id");
        this.title = movie.getString("original_title");
        this.image = movie.getString("poster_path");
        this.image2 = movie.getString("backdrop_path");
        this.overview = movie.getString("overview");
        this.rating = movie.getInt("vote_average");
        this.date = movie.getString("release_date");
        this.popularity = movie.getString("popularity");

    }


    protected Moview(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        image2 = in.readString();
        overview = in.readString();
        date = in.readString();
        rating = in.readInt();
        popularity = in.readString();
    }

    public static final Creator<Moview> CREATOR = new Creator<Moview>() {
        @Override
        public Moview createFromParcel(Parcel in) {
            return new Moview(in);
        }

        @Override
        public Moview[] newArray(int size) {
            return new Moview[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(image2);
        dest.writeString(overview);
        dest.writeString(date);
        dest.writeInt(rating);
        dest.writeString(popularity);
    }
}
