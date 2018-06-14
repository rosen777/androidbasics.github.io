package com.example.android.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<Article>{

    private static final String DATE_SEPARATOR="T";
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The content is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param articles A list of Article objects to display in a list.
     */

    public NewsAdapter(Activity context, ArrayList<Article> articles) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0, articles);
    }

    /**
     * Provide a view for AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data should be displayed in the list item view.
     * @param convertView The recycled View to populate.
     * @param parent The parent ViewGroup that is used
     */

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Article} object located at this position in the list
        Article currentArticle = getItem(position);


        // String date from the Article object
        String dateTime = currentArticle.getDate();

        //Store the resulting Strings
        String date;
        String time;

        if(dateTime.contains(DATE_SEPARATOR)) {
            String[] parts = dateTime.split(DATE_SEPARATOR);
            date = parts[0] + DATE_SEPARATOR;
            time = parts[1];
        } else {
            date = getContext().getString(R.string.empty_separator);
            time = dateTime;
        }

        // String title from the Article objece
        String title = currentArticle.getTitle();

        // Find he TextView in the list_item.xml layout with the ID title
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);

        // Get the title from the current Article object and
        // set this text on the title TextView
        titleTextView.setText(title);

        // Find the TextView in the list_item.xml layout with the ID date
        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);

        // Get the date from the current Article object and
        // set this text on the date TextView
        dateTextView.setText(date);

        // Find the time TextView from the list_item.xml layout with the id time
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time);

        // Get the time from the current Article object and
        // set this text on the time TextView
        timeTextView.setText(time);

        // String url from the Article object
        String url = currentArticle.getUrl();

        // Find the TextView in the list_item.xml layout with the ID url
        TextView urlTextView = (TextView) convertView.findViewById(R.id.url);
        // Get the url from the current Article object and
        // set this text on the url TextView
        urlTextView.setText(url);

        // String section from the Article object
        String section = currentArticle.getSection();

        // Find the TextView in the list_item.xml layout with the ID section
        TextView sectionTextView = (TextView) convertView.findViewById(R.id.section);

        // Get the section Article object and
        // set this text on the section TextView
        sectionTextView.setText(section);

        // String author from the Article object
        String author = currentArticle.getAuthor();

        // Find the TextView in the list_item.xml layout with the ID author
        TextView authorTextView = (TextView) convertView.findViewById(R.id.author);

        // Get the author Article object and
        // set this text on the author TextView
        authorTextView.setText(author);

        // Return the whole list item layout (containing 4 TextViews)
        // so it can shown in the ListView
        return convertView;
    }

}
