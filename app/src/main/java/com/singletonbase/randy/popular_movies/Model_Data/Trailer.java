package com.singletonbase.randy.popular_movies.Model_Data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by randy on 30/05/17.
 */

public class Trailer {

    private String id;
    private String name;
    public String kunci;

    public Trailer(){
    }

    public Trailer(JSONObject trailer) throws JSONException {
        this.kunci = trailer.getString("key");
        this.id = trailer.getString("id");
        this.name = trailer.getString("name");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return kunci;
    }
}
