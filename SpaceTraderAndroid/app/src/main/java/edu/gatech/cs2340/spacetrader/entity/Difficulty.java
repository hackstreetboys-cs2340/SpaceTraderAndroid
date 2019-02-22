package edu.gatech.cs2340.spacetrader.entity;
/**
 * Difficulty enum for game level. Implementing is extra credit.
 */

public enum Difficulty {
    E ("Easy"),
    N ("Normal"),
    H ("Hard"),
    I ("Impossible");

    private final String diff;

    /**
     * Constructor for Difficulty enum.
     *
     * @param diff String representation of attribute
     */
    Difficulty(String diff) {
        this.diff = diff;
    }

    /**
     * Getter method for attribute of enum
     *
     * @return diff String representation of attribute
     */
    public String getDiff() {
        return diff;
    }

    @Override
    public String toString() {
        return diff;
    }
}
