package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<Article>> {

    /** Adapter for the list of earthquakes */

    private NewsAdapter mAdapter;

    /** Constant value for the earthquake loader ID. We can choose any integer.
     * This really comes into play if you're using multiple loaders
     */

    private static final int NEWS_LOADER_ID = 1;

    private static final String LOG_TAG = NewsActivity.class.getName();

    /**
     * URL for the article data from the Guardian
     */

    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=debate%20AND%20economy&tag=politics/politics&from-date=2014-01-01&api-key=test";

    /**
     * TextView that is displayed when the list is empty.
     */

    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(LOG_TAG, "TEST: News Activity onCreate() called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        // Find a reference to the {@link ListView} in the layout

        ListView articleListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list as input
        mAdapter = new NewsAdapter(this, new ArrayList<Article>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated with user interfaces
        articleListView.setEmptyView(mEmptyStateTextView);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        articleListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected article.
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current article that was clicked on
                Article currentArticle = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Internet constructor)
                Uri earthquakeUri = Uri.parse(currentArticle.getUrl());

                // Create a new intent to view the article URL
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
            // Get a reference to the ConnectvityManager to check state of network connectivity
            ConnectivityManager connMgr= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getLoaderManager();

                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid)
                // because this activity implements LoaderCallbacks interface).
                loaderManager.initLoader(NEWS_LOADER_ID, null, this);
            } else {
                // Otherwise, display error
                // First, hide loading indicator so error message will be visible
                View loadingIndicator = findViewById(R.id.progress_bar);
                loadingIndicator.setVisibility(View.GONE);

                // Update empty state with no connection error message
                mEmptyStateTextView.setText(R.string.no_internet_connection);
            }

        }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the list of articles in the response.
     *
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. Our task will take a String URL, and then return an Earthquake. We won't do
     * progress updates, so the generic is just Void.
     *
     * We'll only override two of the methods of AsyncTask: doInBackground(), and onPostExecute().
     * The doInBackground() method runs
     */

    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {
    // Create a new loader for the given URL
    Log.i(LOG_TAG, "TEST: onCreateLoader() called...");
    return new NewsLoader(this, GUARDIAN_REQUEST_URL);
}

    @Override
    public void onLoadFinished(Loader<List<Article>>loader, List<Article> articles) {

    Log.i(LOG_TAG, "TEST: onLoaderReset() called...");

    View loadingIndicator = findViewById(R.id.progress_bar);
    loadingIndicator.setVisibility(View.GONE);

    // Set empty state text to display "No articles found."
    mEmptyStateTextView.setText(R.string.no_articles);

    // Clear the adapter of the previous article data
    mAdapter.clear();

    // If there is a valid list of {@link Article}s, then add them to the adapter's
    // data set. This will trigger the ListView to update.
    if (articles != null && !articles.isEmpty());
    {
        mAdapter.addAll(articles);
    }

    Log.e(LOG_TAG, "");

}

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

        Log.e(LOG_TAG, "");
    }

}
