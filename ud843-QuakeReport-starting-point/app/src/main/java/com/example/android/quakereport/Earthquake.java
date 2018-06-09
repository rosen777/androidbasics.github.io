package com.example.android.quakereport;
public class Earthquake {

    /**
     * {@link Earthquake} represents a single Android platform release.
     * Each object has 3 properties: magnitude, location, and date.
     */

    // Magnitude of the earthquake (e.g. 7.1)
    private double mMagnitude;

    // Location of the earthquake (e.g. San Francisco)
    private String mLocation;

    // URL at USGC for the earthquake (e.g. http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10\)
    private String mUrl;

    // Time of the earthquake (e.g. 11:45)
    private long mTimeInMilliseconds;

    /*
    * Create a new EarthQuake object
    *
    * @param magnitude is the magnitude of the earthquake (e.g. 7.1)
    * @param location is the location of the earthquake (e.g. San Francisco)
    * @param data is the date of the earthquake (e.g. Feb 2., 2016)
    */

    public Earthquake (double Magnitude, String Location, String Url, long TimeInMilliseconds)
    {
        mMagnitude = Magnitude;
        mLocation = Location;
        mTimeInMilliseconds = TimeInMilliseconds;
        mUrl = Url;

    }

    /**
     * Get the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }



    /**
     * Get the location of the earthquake
     */

    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the URL at USGC
     */

    public String getUrl() {
        return mUrl;
    }

    /**
     * Get the date of the earthquake
     */

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }


}
