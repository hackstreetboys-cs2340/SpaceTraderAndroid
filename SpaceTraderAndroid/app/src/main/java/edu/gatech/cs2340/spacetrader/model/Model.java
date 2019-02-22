package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class Model {

    private Repository myRepository;

    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    public static Model getInstance() { return instance;}

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Ship", new ShipInteractor(myRepository));
    }

    /**
     * getter method for player's interactor
     *
     * @return player interactor
     */
    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    /**
     * getter method for ship's interactor
     *
     * @return ship interactor
     */
    public ShipInteractor getShipInteractor() {
        return (ShipInteractor) interactorMap.get("Ship");
    }
}
