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
public class ShopsFragment extends Fragment {


    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);

        // Create a list of places
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.vitosha_blvd), getString(R.string.all_day), R.drawable.boulevard_vitosha));
        places.add(new Place(getString(R.string.paradise_center), getString(R.string.mall_hours), R.drawable.paradise_center));
        places.add(new Place(getString(R.string.serdika_center), getString(R.string.mall_hours), R.drawable.serdika_center));
        places.add(new Place(getString(R.string.sofia_ring_mall), getString(R.string.mall_hours), R.drawable.sofia_ring_mall));

        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places, R.color.category_shops);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be {@link ListView} with the view ID called list, which is declared in the
        // places_list.xml file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link PlaceAdapter} we created aboce, so that the
        // {@link ListView} will display list items for each {@link Place} in the list.
        listView.setAdapter(adapter);

        return rootView;

    }

}

