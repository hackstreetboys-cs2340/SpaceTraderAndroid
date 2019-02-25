package edu.gatech.cs2340.spacetrader.entity;

/**
 * Class for ship of Player.
 */
public class Ship {
    private static ShipType ship;

    /**
     * No args constructor for Ship. Sets ShipType to Gnat.
     */
    public Ship() {this(ShipType.Gnat);}

    /**
     * Full constructor for Ship.
     *
     * @param ship ShipType for ship
     */
    public Ship(ShipType ship) {
        this.ship = ship;
    }

    /**
     * Getter for ship.
     *
     * @return ship
     */
    public ShipType getShip() {
        return ship;
    }

    @Override
    public String toString(){
        return ship.toString();
    }
}
