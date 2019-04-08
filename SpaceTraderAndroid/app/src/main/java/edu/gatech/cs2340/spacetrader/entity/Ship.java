package edu.gatech.cs2340.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.gatech.cs2340.spacetrader.entity.tradegoods.Firearms;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Food;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Furs;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Games;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Machines;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Medicine;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Narcotics;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Ore;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Robots;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Water;

/**
 * Class for ship of Player.
 */
public class Ship {
    private static ShipType ship;
    private List<TradeGood> cargoHold;
    private int size;
    private int capacity;
    private int fuel;
    private int fuelCapacity;
    private int health;

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
        this.capacity = ship.getCapacity();
        this.size = 0;
        this.cargoHold = new ArrayList<>();
        cargoHold.add(new Water());
        cargoHold.add(new Furs());
        cargoHold.add(new Food());
        cargoHold.add(new Ore());
        cargoHold.add(new Games());
        cargoHold.add(new Firearms());
        cargoHold.add(new Medicine());
        cargoHold.add(new Machines());
        cargoHold.add(new Narcotics());
        cargoHold.add(new Robots());
        this.fuelCapacity = ship.getFuelCapacity();
        this.fuel = this.fuelCapacity;
        this.health = ship.getHealth();
    }

    /**
     * Getter for ship.
     *
     * @return ship
     */
    public ShipType getShip() {
        return ship;
    }

    /**
     * get the cargo hold of a ship
     * @return list of cargo
     */
    public List<TradeGood> getCargoHold() {
        return cargoHold;
    }

    /**
     * get the capacity of the ship
     * @return the ship's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * get the size of the ship
     * @return the ship's size
     */
    public int getSize() {
        return size;
    }

    /**
     * get the max fuel capacity of the ship
     * @return the ship's max fuel capacity
     */
    public int getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * get the fuel available for the ship to use
     * @return the ship's current fuel level
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * get the current health of the ship
     * @return health of ship
     */
    public int getHealth() {
        return health;
    }

    /**
     * get name of the ship
     * @return name
     */
    public String getName() {
        return ship.getName();
    }

    /**
     * set the fuel available for the ship to use
     * @param fuel the fuel that is now available for the ship to use
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * set the health of the ship
     * @param health health
     */
    public void setHealth(int health) {
        this.health = health;
    }
    /**
     * removes good from the cargo hold
     *
     * @param good good to be removed
     * @return the cost of the good
     */
    public void remove(TradeGood good) {
        List<String> cargo = new ArrayList<>();
        for (TradeGood i : cargoHold) {
            cargo.add(i.getName());
        }
        if (cargoHold.get(cargo.indexOf(good.getName())).getQuantity() > 0) {
            cargoHold.get(cargo.indexOf(good.getName())).setQuantity(
                    cargoHold.get(cargo.indexOf(good.getName())).getQuantity() - 1);
            size--;
        }
    }

    /**
     * add good to the cargo hold
     *
     * @param good good to be added
     */
    public void add(TradeGood good) {
        if (good.getFinalPrice() > 0) {
            if (testCapacity()) {
                List<String> cargo = new ArrayList<>();
                for (TradeGood i : cargoHold) {
                    cargo.add(i.getName());
                }
                if (good.getFinalPrice() > 0 && testCapacity()) {
                    cargoHold.get(cargo.indexOf(good.getName())).setQuantity(
                            cargoHold.get(cargo.indexOf(good.getName())).getQuantity() + 1);
                    size++;
                }
            }
        }
    }

    /**
     * tests to see if the amount of goods exceeds the capacity
     * @return true if size is less than capacity
     */
    public boolean testCapacity() {
        return size < capacity;
    }


    @Override
    public String toString(){
        String cargo = "";
        for (TradeGood t : cargoHold) {
            cargo += t.getName() + "\n";
        }
        return String.format("ShipType: %s \nCargo Hold\n%s",ship.toString(), cargo);
    }
}
