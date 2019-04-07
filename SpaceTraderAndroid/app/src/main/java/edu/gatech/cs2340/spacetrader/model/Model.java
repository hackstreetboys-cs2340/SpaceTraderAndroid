package edu.gatech.cs2340.spacetrader.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Model Class
 */
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

    /**
     * Getter method for universe interactor
     * @return universe interactor
     */
    public UniverseInteractor getUniverseInteractor() { return (UniverseInteractor) interactorMap.get("Universe"); }

    /**
     * Getter method for buy good interactor
     * @return buy good interactor
     */
    public BuyGoodInteractor getBuyGoodInteractor() {
        return (BuyGoodInteractor) interactorMap.get("BuyGoods");
    }

    /**
     * Getter method for sell good interactor
     * @return sell good interactor
     */
    public SellGoodInteractor getSellGoodInteractor() {
        return (SellGoodInteractor) interactorMap.get("SellGoods");
    }

    /**
     * Getter method for solar system interactor
     * @return solar system interactor
     */
    public SolarSystemInteractor getSolarSystemInteractor() {
        return (SolarSystemInteractor) interactorMap.get("SolarSystems");
    }

    /**
     * Getter method for planet interactor
     * @return planet interactor
     */
    public PlanetInteractor getPlanetInteractor() {
        return (PlanetInteractor) interactorMap.get("Planets");
    }
}
