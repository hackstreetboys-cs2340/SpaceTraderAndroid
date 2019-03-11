package edu.gatech.cs2340.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

/**
 * Class for ship of Player.
 */
public class Ship {
    private static ShipType ship;
    private List<TradeGood> cargoHold;
    private int size;
    private int capacity;

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
        this.cargoHold = new ArrayList<>(capacity);
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
     * removes good from the cargo hold
     *
     * @param good good to be removed
     * @return the cost of the good
     */
    public double remove(TradeGood good) {
        double price = cargoHold.get(cargoHold.indexOf(good)).getFinalPrice();
        if (cargoHold.get(cargoHold.indexOf(good)).getQuantity() > 1) {
            cargoHold.get(cargoHold.indexOf(good)).setQuantity(
                    cargoHold.get(cargoHold.indexOf(good)).getQuantity() - 1);
            return price;
        } else {
            cargoHold.remove(good);
            return price;
        }
    }

    /**
     * add good to the cargo hold
     *
     * @param good good to be added
     * @return cost of the good added
     */
    public void add(TradeGood good) {
        if (good.getFinalPrice() <= 0) {
            return;
        }else if (cargoHold.contains(good) && testCapacity()) {
            cargoHold.get(cargoHold.indexOf(good)).setQuantity(good.getQuantity() + 1);
            size++;
        } else if (testCapacity()) {
            cargoHold.add(good);
            size++;
        } else {
            return;
        }
    }

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
