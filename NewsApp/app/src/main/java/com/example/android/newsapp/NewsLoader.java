package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<Article>>{

    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getSimpleName();

    /** Query URL */
    private String mUrl;

    /**
     * Construct a new {@link NewsLoader}
     * @param context of the activity
     * @param url to load data from
     */

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called...");
        forceLoad();
        Log.e(LOG_TAG, "");
    }

    @Override
    public List<Article> loadInBackground() {

        Log.i(LOG_TAG, "TEST: loadInBackground() called...");

        // Don't perform the request if there are no URLs, or so the first URL is null
        if(mUrl == null) {
            return null;
        }
        Log.e(LOG_TAG, "");

       List<Article>articles = QueryUtils.fetchArticleData(mUrl);

        return articles;

    }

}
