package edu.gatech.cs2340.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Ship;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

/**
 * Repository
 */
public class Repository {

    private Ship myShip;

    private Player myPlayer;

    private Universe myUniverse;

    /**
     * constructor for repository
     */
    public Repository() {
        myPlayer = new Player();
        myShip = new Ship();
    }

    /**
     * getter method for player
     *
     * @return player
     */
    public Player getMyPlayer() {return myPlayer;}

    /**
     * getter method for universe
     * @return universe
     */
    public Universe getMyUniverse() {
        return myUniverse;
    }

    /**
     * setter method for ship
     *
     * @param ship new ship
     */
    public void setMyShip(Ship ship) {this.myShip = ship;}

    /**
     * setter method for player
     *
     * @param myPlayer new player
     */
    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    /**
     * setter method for universe
     *
     * @param myUniverse new universe
     */
    public void setMyUniverse(Universe myUniverse) { this.myUniverse = myUniverse; }

    /**
     * gets the trade goods for a given planet
     * @param planet planet
     * @return list of goods
     */
    public List<TradeGood> getTradeGoodsForPlanet(Planet planet) {
        List<TradeGood> toReturn = new ArrayList<>();
        for(TradeGood t : planet.getGoods()) {
            toReturn.add(t);
        }
        return toReturn;
    }

    /**
     * gets the trade goods for a given player
     * @param player player
     * @return list of goods
     */
    public List<TradeGood> getTradeGoodsForPlayer(Player player) {
        List<TradeGood> toReturn = new ArrayList<>();
        for (TradeGood t : player.getCargoHold()) {
            toReturn.add(t);
        }
        return toReturn;
    }

    /**
     * gets a list of solar systems for a given universe
     * @param universe universe
     * @return list of solar systems
     */
    public List<SolarSystem> getSolarSystemsForUniverse(Universe universe) {
        List<SolarSystem> toReturn = new ArrayList<>();
        for (SolarSystem s : universe.getSolarSystems()) {
            toReturn.add(s);
        }
        return toReturn;
    }

    /**
     * gets a list of planets for a given solar system
     * @param system solar system
     * @return list of planets
     */
    public List<Planet> getPlanetsForSystem(SolarSystem system) {
        List<Planet> toReturn = new ArrayList<>();
        for (Planet p : system.getPlanets()) {
            toReturn.add(p);
        }
        return toReturn;
    }

    /**
     * player purchases a given good
     * @param tradeGood good to be bought
     */
    public void addPlayerGood(TradeGood tradeGood) {
        myPlayer.buy(tradeGood);
    }

    /**
     * player sells a good
     * @param tradeGood good to be sold
     */
    public void removePlayerGood(TradeGood tradeGood) {
        myPlayer.sell(tradeGood);
    }
}
