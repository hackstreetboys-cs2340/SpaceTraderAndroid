package edu.gatech.cs2340.spacetrader.entity;

import java.util.Arrays;
import java.util.List;

public class Ship {
    private static ShipType ship;

    public Ship(ShipType ship) {
        this.ship = ship;
    }

    public ShipType getShip() {
        return ship;
    }

    public String toString(){
        return ship.toString();
    }
}
