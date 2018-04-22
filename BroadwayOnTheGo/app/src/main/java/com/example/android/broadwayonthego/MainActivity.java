package com.example.android.broadwayonthego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the Rent Musical category
        TextView rent = (TextView) findViewById(R.id.rent);

        rent.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Rent Musical category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SongActivity}
                Intent rentIntent = new Intent(MainActivity.this, RentActivity.class);

                // Start the new activity
                startActivity(rentIntent);
            }
        });

        // Find the View that shows the Wicked Musical category
        TextView wicked = (TextView) findViewById(R.id.wicked);

        wicked.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Wicked Musical category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SongActivity}
                Intent wickedIntent = new Intent(MainActivity.this, WickedActivity.class);

                // Start the new activity
                startActivity(wickedIntent);
            }
        });

        TextView aladdin = (TextView) findViewById(R.id.aladdin);

        aladdin.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Aladdin Musical category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link SongActivity}
                Intent aladdinIntent = new Intent(MainActivity.this, AladdinActivity.class);

                //Start the new activity
                startActivity(aladdinIntent);
            }
        });

    }
}
