package com.example.android.broadwayonthego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class WickedActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of songs
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("No One Mourns The Wicked\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("Dear Old Shiz\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("The Wizard and I\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("What is This Feeling?\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("Something Bad\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("Dancing Through Life\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("Popular\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("I'm Not That Girl\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("One Short Day\n", "Wicked Musical Cast", R.drawable.wickedcover));
        songs.add(new Song("A Sentimental Man\n", "Wicked Musical Cast", R.drawable.wickedcover));

        // Create an {@link SongAdapter}, whose data source is a list of {@link Songs}. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the View ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SongAdapter, we created above, so that the
        // {@link ListView} will display list items for each {@link Song} in the list.
        listView.setAdapter(adapter);

    }
}
