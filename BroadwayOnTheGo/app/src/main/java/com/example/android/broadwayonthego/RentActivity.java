package com.example.android.broadwayonthego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of songs
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Seasons of Love\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Rent\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("You'll See\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("One Song Glory\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Light My Candle\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Today 4 U\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Tango: Maureen\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Life Support\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Out Tonight\n", "Rent Musical Cast", R.drawable.rentcover));
        songs.add(new Song("Another Day\n", "Rent Musical Cast", R.drawable.rentcover));

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

        setContentView(R.layout.list_item);

        Button library = (Button) findViewById(R.id.library_back);

        library.setOnClickListener(new View.OnClickListener() {
            //The code in this method will be executed when the library button is clicked on
            @Override
            public void onClick(View view) {
                Intent libraryIntent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(libraryIntent);
            }
        });

    }
}
