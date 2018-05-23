package com.example.android.tourguidesofia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);

        //  Create a list of words
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place("Bacchus StrEAT Fest 2018", "June 8 & 9, 2018, 10:00am - 8:00pm"));
        places.add(new Place("Mish Mash Fest", "June 9 & 10, 2018, 10:00am - 7:00pm"));
        places.add(new Place("Radio Park Fest", "June 15, 16 & 17 2018, 6:00pm"));
        places.add(new Place("HippieLandia Festival 2018", "June 22, 2018, 9:00pm – 8:00am"));


        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places, R.color.category_events);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView with the ID called list, which is declared in the
        // place_list.xml file
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link PlaceAdapter} we created above, so that
        // {@link ListView} will display list items for each {@link Place} in the list.
        listView.setAdapter(adapter);

        return rootView;
    }
}
