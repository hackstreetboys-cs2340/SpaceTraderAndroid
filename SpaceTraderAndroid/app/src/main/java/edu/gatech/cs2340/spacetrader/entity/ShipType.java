package edu.gatech.cs2340.spacetrader.entity;

/**
 * ShipType enum for Ship class.
 */
public enum ShipType {
    Gnat ("Gnat", 15, 20);

    private final String name;
    private final int capacity;
    private final int fuelCapacity;

    /**
     * Full constructor for ShipType.
     *
     * @param name name of ShipType
     */
    ShipType(String name, int capacity, int fuelCapacity) {
        this.name = name;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
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

    /**
     * getter for the ship's fuel capacity
     *
     * @return fuel capacity
     */
    public int getFuelCapacity() { return fuelCapacity; }

    @Override
    public String toString() {
        return name;
    }
}
