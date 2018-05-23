package com.example.android.tourguidesofia;

/**
 * {@link Place} represents a place or a sight in Sofia that the user wants to learn more about.
 * It contains a name and opening hours of that place.
 */

public class Place {

    /**
     * Name for the place
     */
    private String mName;

    /**
     * Hours of the place
     */
    private String mHours;

    /**
     * Image resource ID for the place
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /**
     * Constant value that represents no image was provided for the place
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Place object
     *
     * @param name  is the name of the place or sight
     * @param hours is the business hours when the place is open
     */

    public Place(String name, String hours) {
        mName = name;
        mHours = hours;
    }

    /**
     * Create a new Place object
     *
     * @param name            is the name of the place or sight
     * @param hours           is the opening hours of the place
     * @param imageResourceId is the resource ID for the image associated with this place
     */

    public Place(String name, String hours, int imageResourceId) {
        mName = name;
        mHours = hours;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the place
     */

    public String getName() {
        return mName;
    }

    /**
     * Get the hours of operation of the place
     */

    public String getHours() {
        return mHours;
    }

    /**
     * Return the resource ID of the place
     */

    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Return whether or not there is an image for this place
     */

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


    @Override
    public String toString() {
        return "Place{" +
                "mName='" + mName + '\'' +
                ", mHours='" + mHours + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
}
