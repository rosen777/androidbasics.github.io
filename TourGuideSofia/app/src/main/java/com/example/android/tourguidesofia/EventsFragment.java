package com.example.android.tourguidesofia;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


        /**
         * Handles playback of all the sound files
         */
        private MediaPlayer mMediaPlayer;

        /**
         * Handles playback when playing a sound file
         */
        private AudioManager mAudioManager;

        /**
         * This listener gets triggered whenever the audio focus changes
         * (i.e., we gain or lose audio focus because of another app or device).
         */

        private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int focusChange) {
                if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                    // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume. We'll treat
                    // both cases the same way because our app is playing short sound files.

                    // Pause playback and reset player to the start of the file. That way, we can
                    // play the word from the beginning when we resume playback.
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    mMediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    //The AUDIOFOCUS_LOSS case means have lost audio focus and
                    // Stop playback and clean up resource
                    releaseMediaPlayer();
                }
            }
        };

        private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Now that sound file has finished playing, release media resources
                releaseMediaPlayer();
            }
        };

        public EventsFragment() {
            // Required empty public constructor
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);

            //  Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

            //  Create a list of words
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place("Vodenicata","10:00am - 11:00pm", R.drawable.vodenicata));
        places.add(new Place("Melnicite", "09:00am - 11:00pm", R.drawable.melnicite));
        places.add(new Place("Shastliveca", "12:00pm - 10:00pm". R.drawable.shastliveca));
        places.add(new Place("Kushtata", "11:00am - 11:00om", R.drawable.kushatata));


        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places, R.color.category_places);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView with the ID called list, which is declared in the
        // place_list.xml file
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link PlaceAdapter} we created above, so that
        // {@link ListView} will display list items for each {@link Place} in the list.
        listView.setAdapter(adapter);

        //Set a click listener to play the audio when the list item is clicked onn
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            //  Get the {@link Place} object at the given position the user clicked on

            //  Release the media player if it currently exists because we are about to
            //  play a differerent file
            releaseMediaPlayer();

            Place place = places.get(position;

            // Request audio focus so in order to play the audio file. The app needs to play a
            // short audio file, so we will request audio focus with a short amount of time
            // with AUDIOFOCUS_GAIN_TRANSIENT
            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED {
                //  We have audio focus now.

                //  Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the 
            }
        }
        }


}
