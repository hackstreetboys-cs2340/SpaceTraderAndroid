package edu.gatech.cs2340.spacetrader.entity;

/**
 * Player class including attributes of player for the game.
 */
public class Player {
    private int pilotSkill, engSkill, tradeSkill, fightSkill;
    private String name;
    private Ship ship;
    private Difficulty difficulty;

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
     * Getter for Difficulty.
     *
     * @return difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
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
     * Setter for Difficulty.
     *
     * @param difficulty difficulty level
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Pilot Skill: %d, Engineering Skill: %d, Trade Skill: %d," +
                " Fighting Skill: %d, Ship: %s, Difficulty: %s", name, pilotSkill, engSkill, tradeSkill,
                fightSkill, ship.toString(), difficulty.toString());
    }

}
