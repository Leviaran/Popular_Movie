package com.singletonbase.randy.popular_movies.Model_Data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by randy on 30/05/17.
 */

public class Moview implements Parcelable {

    private int id;
    private String title;
    private String image;
    private String image2;
    private String overview;
    private String date;
    private int rating;

    public Moview(){

    }

    public int getId() {
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

    public Moview(Cursor cursor){
        this.id=cursor.getInt(Fragment_main.COL_MOVIEW_ID);
        this.id=cursor.getString(Fragment_main.COL_TITLE);
        this.image= cursor.getString(Fragment_main.COL_IMAGE);
        this.image2= cursor.getString(Fragment_main.COL_IMAGE2);
        this.overview= cursor.getString(Fragment_main.COL_OVERVIEW);
        this.date= cursor.getString(Fragment_main.COL_DATE);
        this.rating= cursor.getInt(Fragment_main.COL_RATING);
    }


    protected Moview(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        image2 = in.readString();
        overview = in.readString();
        date = in.readString();
        rating = in.readInt();
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
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(image2);
        dest.writeString(overview);
        dest.writeString(date);
        dest.writeInt(rating);
    }
}
