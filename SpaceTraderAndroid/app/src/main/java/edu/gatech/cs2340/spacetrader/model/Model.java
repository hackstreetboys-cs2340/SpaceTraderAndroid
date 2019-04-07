package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class Model {

    private Repository myRepository;

    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    /**
     * getter method for instance
     *
     * @return this instance (of type Model)
     */
    public static Model getInstance() { return instance;}

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Ship", new ShipInteractor(myRepository));
        interactorMap.put("Universe", new UniverseInteractor(myRepository));
        interactorMap.put("BuyGoods", new BuyGoodInteractor(myRepository));
        interactorMap.put("SellGoods", new SellGoodInteractor(myRepository));
        interactorMap.put("SolarSystems", new SolarSystemInteractor(myRepository));
        interactorMap.put("Planets", new PlanetInteractor(myRepository));
        interactorMap.put("Database", new DatabaseInteractor());
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

    public UniverseInteractor getUniverseInteractor() { return (UniverseInteractor) interactorMap.get("Universe"); }

    public BuyGoodInteractor getBuyGoodInteractor() {
        return (BuyGoodInteractor) interactorMap.get("BuyGoods");
    }

    public SellGoodInteractor getSellGoodInteractor() {
        return (SellGoodInteractor) interactorMap.get("SellGoods");
    }

    public SolarSystemInteractor getSolarSystemInteractor() {
        return (SolarSystemInteractor) interactorMap.get("SolarSystems");
    }

    public PlanetInteractor getPlanetInteractor() {
        return (PlanetInteractor) interactorMap.get("Planets");
    }

    public DatabaseInteractor getDatabaseInteractor() {
        return (DatabaseInteractor) interactorMap.get("Database");
    }
}
