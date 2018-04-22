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

public class AladdinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create a list of songs
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Overture\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("Arabian Nights\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("One Jump Ahead\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("Proud of Your Boy\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("These Palace Walls\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("Babkak, Omar, Aladdin, Kassim\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("A Million Miles Away\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("Diamond in the Rough\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("Friend Like Met\n", "Aladdin Musical Cast", R.drawable.aladdincover));
        songs.add(new Song("Act I Finale\n", "Aladdin Musical Cast", R.drawable.aladdincover));

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

        Button libraryView = (Button) findViewById(R.id.libraryButton);

        libraryView.setOnClickListener(new View.OnClickListener() {
            //  The code in this function would be executed when the back to library button is clicked on.
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link AladdinActivity}
                Intent libraryIntent = new Intent(getApplicationContext(), MainActivity.class);

                //Start the new activity
                startActivity(libraryIntent);
            }
        });


    }
}
