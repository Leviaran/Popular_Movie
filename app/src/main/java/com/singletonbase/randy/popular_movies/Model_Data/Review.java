package com.singletonbase.randy.popular_movies.Model_Data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by randy on 30/05/17.
 */

public class Review {

    private String id;
    private String Author;
    private String isi;

    public Review(){

    }

    public String getId() {
        return id;
    }

    public Review(JSONObject trailer) throws JSONException {
        this.id = trailer.getString("id");
        this.Author = trailer.getString("author");
        this.isi = trailer.getString("content");

    }

    public String getAuthor() {
        return Author;
    }

    public String getIsi() {
        return isi;
    }


}
