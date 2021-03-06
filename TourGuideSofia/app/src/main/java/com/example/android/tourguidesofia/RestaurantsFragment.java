package com.example.android.tourguidesofia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.place_list, container, false);

        // Create a list of places
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.vodenicata), getString(R.string.vodenicata_hours), R.drawable.vodenicata));
        places.add(new Place(getString(R.string.vqtyrnite_melnici), getString(R.string.vqtyrnite_melnici_hours), R.drawable.vqtyrni_melnici));
        places.add(new Place(getString(R.string.shastliveca), getString(R.string.shastliveca_hours), R.drawable.shastliveca));
        places.add(new Place(getString(R.string.kyshtata_s_chasovnika), getString(R.string.kyshtata_s_chasovnika_hours), R.drawable.kushtata_s_chasovnika));

        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list.

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places, R.color.category_restaurants);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be {@link ListView with the ID called list, which is declared in the
        // place_list.xml file
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link Listview} use the {@link PlaceAdapter} we created above, so that
        // {@link ListView} will display list items for each {@link Place} in the list.
        listView.setAdapter(adapter);

        return rootView;

    }

}
