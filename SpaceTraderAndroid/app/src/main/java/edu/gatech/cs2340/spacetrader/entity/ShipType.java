package edu.gatech.cs2340.spacetrader.entity;

/**
 * ShipType enum for Ship class.
 */
public enum ShipType {
    Gnat ("Gnat", 15);

    private final String name;
    private final int capacity;

    /**
     * Full constructor for ShipType.
     *
     * @param name name of ShipType
     */
    ShipType(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Getter for name of ShipType.
     *
     * @return name of ship
     */
    public String getName() {
        return name;
    }

    /**
     * getter for capacity of ship
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name;
    }
}
