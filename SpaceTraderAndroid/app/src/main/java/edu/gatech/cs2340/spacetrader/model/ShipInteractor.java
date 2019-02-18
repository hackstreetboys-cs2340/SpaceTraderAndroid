package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Ship;

public class ShipInteractor extends Interactor {

    public ShipInteractor(Repository repo) {
        super(repo);
    }

    public void setMyShip(Ship ship) {
        getRepository().setMyShip(ship);
    }
}
