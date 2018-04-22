package com.example.android.broadwayonthego;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    /**
     * {@link SongAdapter is an {@link ArrayAdapter} that can provide the layout for each list
     * based on a data source, which is a list of {@link Song} objects.
     */

    public class SongAdapter extends ArrayAdapter<Song> {
        private static final String Song = SongAdapter.class.getSimpleName();

        /**
         * This is our own constructor (it doesn't mirror a superclass constructor).
         * The context is used to inflate the layout file, and the list is the data we want
         * to populate into the list.
         *
         * @param context The current context. Used to inflate the layout file.
         * @param songs A list of song objects we want to display in a list.
         */

        public SongAdapter(Activity context, ArrayList<Song> songs) {
            // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
            // the second argument is used when the ArrayAdapter is populating a single TextView.
            // Because this is a custom adapter for two TextViews, the adapter is not
            // going to use this second argument, so it can be any value. Here, we used 0.
            super(context, 0, songs);
        }

        /**
         *Provides a view for an AdapterView (ListView, GridView, etc.)
         *
         * @param position The position in the list of data that hould be displayed in the list item view.
         * @param convertView The recycled view to populate.
         * @param parent The parent ViewGroup that is used for inflation.
         * @return The View for the position in the AdapterView.
         */
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link Song} object located at this position in the list
            Song currentSong = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID song_title
            TextView titleTextView = (TextView) listItemView.findViewById(R.id.song_title);
            // Get the song title from the current Song object and
            // set this text on the title TextView
            titleTextView.setText(currentSong.getSongTitle());

            // Find the TextView in the list_item.xml layout with the ID artist_name
            TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist_name);
            // Get the artist name from the current Song object and
            // set this text on the artist TextView
            artistTextView.setText(currentSong.getArtistName());

            //Find the ImageView in the list_item.xml layout with the ID album_cover
            ImageView coverView = (ImageView) listItemView.findViewById(R.id.list_item_cover);
            //Get the image resource ID from the current Song object and
            // set the image to coverView
            coverView.setImageResource(currentSong.getImageResourceId());

            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            return listItemView;
        }

    }


