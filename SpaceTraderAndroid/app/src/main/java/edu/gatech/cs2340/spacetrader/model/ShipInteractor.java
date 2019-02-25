package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Ship;

public class ShipInteractor extends Interactor {

    /**
     * constructor method for ship's interactor
     *
     * @param repo respository
     */
    public ShipInteractor(Repository repo) {
        super(repo);
    }

    /**
     * setter for ship to interact (accesses Repository's setter)
     *
     * @param ship ship
     */
    public void setMyShip(Ship ship) {
        getRepository().setMyShip(ship);
    }
}
