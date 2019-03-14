package edu.gatech.cs2340.spacetrader.entity;
/**
 * Difficulty enum for game level. Implementing is extra credit.
 */

public enum Difficulty {
    E ("Easy", 15000),
    N ("Normal", 10000),
    H ("Hard", 7500),
    I ("Impossible", 5000);

    private final String diff;
    private final double wallet;

    /**
     * Constructor for Difficulty enum.
     *
     * @param diff String representation of attribute
     */
    Difficulty(String diff, double wallet) {
        this.diff = diff;
        this.wallet = wallet;
    }

    /**
     * Getter method for attribute of enum
     *
     * @return diff String representation of attribute
     */
    public String getDiff() {
        return diff;
    }

    /**
     * gets the initial wallet of player
     * @return available $ amount
     */
    public double getWallet() {
        return wallet;
    }

    @Override
    public String toString() {
        return diff;
    }
}
