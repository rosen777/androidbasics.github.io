package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the background color for this list of words */

    private int mColorResourceId;

    /**
     * Create a new {@link WordAdapter} object.
     *
     * @param context       The current context. Used to inflate the layout file.
     * @param words    A list of word objects to display in a list.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */

    private static final String Word = WordAdapter.class.getSimpleName();

        /**
         * This is our own custom constructor (it doesn't mirror a superclass constructor).
         * The context is used to inflate the layout file, and the list is the data we want
         * to populate into the lists.
         *
         * @param context       The current context. Used to inflate the layout file.
         * @param words    A list of word objects to display in a list.
         */

        public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView
            // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not,
            //going to use this second argument, so it can be any value. Here, we used 0.
            super(context, 0, words);
            mColorResourceId = colorResourceId;
        }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should displayed in the list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the link {@link Words} object locate at this position in the list
            Word currentWord = getItem(position);


            //Find the TextView in the list_item.xml layout with the ID miwok translation
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            // Get the version name from the current Word object with the ID miwok translation
            // set this text on the name TextView
            miwokTextView.setText(currentWord.getMiwokTranslation());

            //Find the TextView in the list_item.xml layout with the ID english translation
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            // Get the version number from the current Word object and
            // set this text on the number TextView
            defaultTextView.setText(currentWord.getDefaultTranslation());

            //Find the ImageView in the list_item.xml layout with the ID image.
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            //Check if an image is provided for this word or not
            if(currentWord.hasImage()) {
                // If an image is available, display the provided image based on resource ID
                imageView.setImageResource(currentWord.getImageResourceId());
                // Make sure the view is visible
                imageView.setVisibility(View.VISIBLE);
            } else {
                // Otherwise hide the ImageView (set visibility to GONE)
                imageView.setVisibility(View.GONE);
            }

            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);

            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);

            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView

            return listItemView;

        }

}
