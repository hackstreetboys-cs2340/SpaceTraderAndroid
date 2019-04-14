package edu.gatech.cs2340.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

/**
 * Player class including attributes of player for the game.
 */
public class Player {
    private int pilotSkill, engSkill, tradeSkill, fightSkill;
    private String name;
    private Ship ship;
    private Difficulty difficulty;
    private long seed;
    private double wallet;



    private Planet location;

    /**
     * No args constructor for Player class.
     */
    public Player () { this(""); }

    /**
     * Constructor only taking in name for Player class.
     *
     * @param name name of Player
     */
    public Player(String name) {
        this(name, 0, 0, 0, 0, Difficulty.N);
    }

    /**
     * Constructor taking in everything but ShipType.
     *
     * @param name name of Player
     * @param pilotSkill pilot skill points
     * @param engSkill engine skill points
     * @param tradeSkill trade skill points
     * @param fightSkill fight skill points
     * @param difficulty Difficulty of game
     */
    public Player(String name, int pilotSkill, int engSkill, int tradeSkill, int fightSkill, Difficulty difficulty) {
        this(name, pilotSkill, engSkill, tradeSkill, fightSkill, new Ship(ShipType.Gnat), difficulty);
    }

    /**
     * Full constructor for Player class.
     *
     * @param name name of Player
     * @param pilotSkill pilot skill points
     * @param engSkill engine skill points
     * @param tradeSkill trade skill points
     * @param fightSkill fight skill points
     * @param ship ShipType of game
     * @param difficulty Difficulty of game
     */
    public Player(String name, int pilotSkill, int engSkill, int tradeSkill, int fightSkill, Ship ship, Difficulty difficulty) {
        this.name = name;
        this.pilotSkill = pilotSkill;
        this.engSkill = engSkill;
        this.tradeSkill = tradeSkill;
        this.fightSkill = fightSkill;
        this.ship = ship;
        this.difficulty = difficulty;
        this.wallet = difficulty.getWallet();
    }

    /**
     * Getter for name.
     *
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for pilot skill points.
     *
     * @return pilotSkill
     */
    public int getPilotSkill() {
        return pilotSkill;
    }

    /**
     * Getter for engine skill points.
     *
     * @return engSkill
     */
    public int getEngSkill() {
        return engSkill;
    }

    /**
     * Getter for trade skill points.
     *
     * @return tradeSkill
     */
    public int getTradeSkill() {
        return tradeSkill;
    }

    /**
     * Getter for fight skill points.
     *
     * @return fightSkill
     */
    public int getFightSkill() {
        return fightSkill;
    }

    /**
     * Getter for ShipType.
     *
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Getter for wallet
     *
      * @return wallet
     */
    public double getWallet() {
        return wallet;
    }

    /**
     * getter for planet where the player is located
     * @return location
     */
    public Planet getLocation() {
        return location;
    }

    /**
     * get the cargo hold of the player's ship
     * @return list of trade goods
     */
    public List<TradeGood> getCargoHold() {
        return ship.getCargoHold();
    }

    /**
     * get the capacity of the player's ship's cargo hold
     * @return capacity of cargo hold
     */
    public int getCapacity() {
        return ship.getCapacity();
    }

    /**
     * get the number of goods in the player's ship's cargo hold
     * @return number of goods in cargo hold
     */
    public int getSize() {
        return ship.getSize();
    }

    /**
     * get the name of the player's ship
     * @return name of ship
     */
    public String getShipName() {
        return ship.getName();
    }

    /**
     * Setter for player name.
     *
     * @param name name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for pilot skill points.
     *
     * @param pilotSkill pilot skill points
     */
    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    /**
     * Setter for engine skill points.
     *
     * @param engSkill engine skill points
     */
    public void setEngSkill(int engSkill) {
        this.engSkill = engSkill;
    }

    /**
     * Setter for trade skill points.
     *
     * @param tradeSkill trade skill points
     */
    public void setTradeSkill(int tradeSkill) {
        this.tradeSkill = tradeSkill;
    }

    /**
     * Setter for fight skill points.
     *
     * @param fightSkill fight skill points
     */
    public void setFightSkill(int fightSkill) {
        this.fightSkill = fightSkill;
    }

    /**
     * Setter for Difficulty
     *
     * @param difficulty difficulty level
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        setWallet(difficulty.getWallet());
    }

    /**
     * Setter for wallet
     *
     * @param wallet wallet amount
     */
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    /**
     * setter for location
     * @param location planet where the player is
     */
    public void setLocation(Planet location) {
        this.location = location;
    }

    /**
     * player buys a good and updates their wallet
     *
     * @param good good being bought
     */
    public void buy(TradeGood good) {
        if (ship.testCapacity()) {
            ship.add(good);
            if (good.getFinalPrice() >= 0) {
                wallet -= good.getFinalPrice();
            }
        }
    }

    /**
     * player sells a good and updates their wallet
     *
     * @param good good being sold
     */
    public void sell(TradeGood good) {
        if (ship.getSize() != 0) {
            List<String> planetGoods = new ArrayList<>();
            for (TradeGood i : location.getGoods()) {
                planetGoods.add(i.getName());
            }
            good = location.getGoods().get(planetGoods.indexOf(good.getName()));
            ship.remove(good);
            wallet += good.getFinalPrice();
        }
    }

    /**
     * player travels to another planet
     * calculates a possible event that could occur
     * subtracts the distance from the ship's fuel
     * sets the current location of the player to the new planet
     * @param location planet to travel to
     * @return travel was successful = true else false
     */
    public boolean travel(Planet location) {
        if (ship.getFuel() >= this.location.distanceTo(location.getCoordinates())) {
            Random rnd = new Random();
            int chance = rnd.nextInt(200);

            if (chance >= 0 && chance < 20) {
                ship.setHealth((int) (ship.getHealth() * .75));
            }
            else if (chance >= 20 && chance < 25) {
                ship.setHealth((int) (ship.getHealth() * .33));
            }

            ship.setFuel(ship.getFuel() - this.location.distanceTo(location.getCoordinates()));
            setLocation(location);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setter for seed
     * @param seed the seed
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Pilot Skill: %d, Engineering Skill: %d, Trade Skill: %d," +
                " Fighting Skill: %d,\nShip: %sDifficulty: %s,\nWallet: %.2f\nLocation: %s", name, pilotSkill, engSkill, tradeSkill,
                fightSkill, ship.toString(), difficulty.toString(), wallet, location);
    }

}
