package edu.gatech.cs2340.spacetrader.entity;

/**
 * Respresents location as a point (latitude, longitude)
 */
public class Coordinates {
    private double latitude;
    private double longitude;

    /**
     * Constructor for Coordinates
     * @param latitude
     * @param longitude
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * No arg constructor for Coordinates
     */
    public Coordinates() {
        this(0,0);
    }

    /**
     * Longitude getter
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Longitude setter
     * @param longitude the longitude to be set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Latitude getter
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Latitude setter
     * @param latitude the latitude to be set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
