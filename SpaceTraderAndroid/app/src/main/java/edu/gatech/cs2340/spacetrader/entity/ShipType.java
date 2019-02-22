package edu.gatech.cs2340.spacetrader.entity;

/**
 * ShipType enum for Ship class.
 */
public enum ShipType {
    Gnat ("Gnat");

    private final String name;

    /**
     * Full constructor for ShipType.
     *
     * @param name name of ShipType
     */
    ShipType(String name) {
        this.name = name;
    }

    /**
     * Getter for name of ShipType.
     *
     * @return name of ship
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
