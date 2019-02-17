package edu.gatech.cs2340.spacetrader.entity;

public enum Difficulty {
    E ("Easy"),
    N ("Normal"),
    H ("Hard"),
    I ("Impossible");

    private final String diff;

    Difficulty(String diff) {
        this.diff = diff;
    }

    public String getDiff() {
        return diff;
    }

    public String toString() {
        return diff;
    }
}
