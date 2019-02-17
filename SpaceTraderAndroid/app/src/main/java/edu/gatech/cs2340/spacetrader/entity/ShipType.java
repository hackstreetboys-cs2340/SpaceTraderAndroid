package edu.gatech.cs2340.spacetrader.entity;

public enum ShipType {
    Gnat ("Gnat");

    private final String name;

    ShipType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
