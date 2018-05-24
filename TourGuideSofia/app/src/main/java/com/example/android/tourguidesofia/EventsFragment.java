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
        places.add(new Place(getString(R.string.bacchus), getString(R.string.bacchus_hours)));
        places.add(new Place(getString(R.string.mish_mash_fest), getString(R.string.mish_mash_fest_hours)));
        places.add(new Place(getString(R.string.radio_park_fest), getString(R.string.radio_park_fest_hours)));
        places.add(new Place(getString(R.string.hippieland), getString(R.string.hippieland_hours)));


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
