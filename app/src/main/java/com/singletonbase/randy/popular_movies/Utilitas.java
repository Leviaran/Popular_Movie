package com.singletonbase.randy.popular_movies;

import android.content.Context;
import android.database.Cursor;

import com.singletonbase.randy.popular_movies.Data.MovieList;

/**
 * Created by randy on 30/05/17.
 */

public class Utilitas {
    public static int isFavorite(Context context, String id){

        Cursor cursor = context.getContentResolver().query(
                MovieList.MovieEntry.CONTENT_URI, null,
                MovieList.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                new String[]{id}, null
        );
        int numRows = cursor.getCount();
        cursor.close();
        return numRows;
    }

    public static String BuildeImageURL(int width, String file){
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + file;
    }
}
