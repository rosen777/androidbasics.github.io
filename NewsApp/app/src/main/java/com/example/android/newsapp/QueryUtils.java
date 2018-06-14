package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class QueryUtils {

    /**
     * Helper method related to requesting and receiving articles data from The Guardian
     */

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /* Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */

    private QueryUtils() {

    }

    /**
     * Query The Guardian dataset and return a list of {@link Article} objects
     */

    public static List<Article> fetchArticleData(String requestUrl) {

        Log.i(LOG_TAG, "TEST: fetchArticleData() called...");

        // Create URL Object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and received a JSON response back
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch(IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link New}s
        List<Article> articles = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Articles}
        return articles;

    }

    /**
     * Return new URL object from the given string URL
     */

    private static URL createUrl(String stringUrl) {

        // Create URL object

        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code:" + urlConnection.getResponseCode());
            }
        }
            catch (IOException e) {
                Log.e(LOG_TAG,"Problem retrieving the articles JSON results.", e);
            } finally {
                if (urlConnection != null) {
                urlConnection.disconnect();
                }
                if(inputStream != null) {
                    // Closing the input stream throw an IOException, which is why
                    // the makeHttpRequest(URL url) method signature specifies than an IOException
                    // could be thrown.
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */

        private static String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return the {@link Article} objects that has been build up from
         * parsing the giben JSON response.
         */

        private static List<Article> extractFeatureFromJson(String articleJSON) {
            //If the JSON string is empty or null, then return early
            if (TextUtils.isEmpty(articleJSON)) {
                return null;
            }

            // Create an empty ArrayList that we can start adding articles to
            List<Article> articles = new ArrayList<>();

            // Try to parse JSON response string. If there's a problem with the way the JSON
            // is formatted, a JSONException exception object will be thrown.
            // Catch the expectation so the app doesn't crash, and print the error message to the logs.

            try{

                // Get a single piece of articles at the position i within the list of articles
                JSONObject baseJsonResponse = new JSONObject(articleJSON);

                // Extract the JSONArray with the key called results
                JSONObject response = baseJsonResponse.getJSONObject("response");

                // Extract the JSONArray associated with the key called "features",
                // which represents a list of features (or articles).

                JSONArray articleArray = response.getJSONArray("results");

                // If there are results in the articles array
                for (int i = 0; i < articleArray.length(); i++) {

                    // Get a single piece of articles at the position i within the list of articles
                    JSONObject currentNews = articleArray.getJSONObject(i);

                    // Extract the value for the key called "webTitle"
                    String title = currentNews.getString("webTitle");

                    // Extract the value for the key called "sectionName"
                    String section = currentNews.getString("sectionName");

                    // Extract the value for the key called "webPublicationDate"
                    String date = currentNews.getString("webPublicationDate");

                    JSONArray tagsArray = currentNews.getJSONArray("tags");

                        JSONObject currentTags = tagsArray.getJSONObject(i);

                        // Extract the value for the key called "author"
                        String author = currentTags.getString("webTitle");

                    // Extract the value for the key called "url"
                    String url = currentNews.getString("url");

                    // Create the new {@link Earthquake} object with magnitude, location, time
                    // and url from the JSON response.
                    Article article = new Article(title, section, date, url, author);

                    articles.add(article);

                }
            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.

                Log.e("QueryUtils", "Problem parsing the articles JSON result", e);
            }

            // Return the list of articles
            return articles;

        }

    }

