package com.example.android.broadwayonthego;

/**
 * {@link Song} represents a song from a Broadway show.
 * It contains the song title and the artist name for that song.
 */

public class Song {

    // Song title for the song
    private String mSongTitle;

    // Artist name for the song
    private String mArtistName;

    //Name of the resource
    private int mImageResourceId;

    public Song(String songTitle, String artistName, int imageResourceId) {

        mSongTitle = songTitle;
        mArtistName = artistName;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the default song title.
     */

    public String getSongTitle() {
        return mSongTitle;
    }

    /**
     * Get the default artist name.
     */

    public String getArtistName(){
        return mArtistName;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }
}
