package edu.gatech.cs2340.spacetrader.entity;

public class Player {
    private int pilotSkill, engSkill, tradeSkill, fightSkill;
    private String name;
    private Ship ship;
    private Difficulty difficulty;

    public Player(String name) {
        this(name, 0, 0, 0, 0, Difficulty.N);
    }
    public Player(String name, int pilotSkill, int engSkill, int tradeSkill, int fightSkill, Difficulty difficulty) {
        this(name, pilotSkill, engSkill, tradeSkill, fightSkill, new Ship(ShipType.Gnat), difficulty);
    }
    public Player(String name, int pilotSkill, int engSkill, int tradeSkill, int fightSkill, Ship ship, Difficulty difficulty) {
        this.name = name;
        this.pilotSkill = pilotSkill;
        this.engSkill = engSkill;
        this.tradeSkill = tradeSkill;
        this.fightSkill = fightSkill;
        this.ship = ship;
        this.difficulty = difficulty;
    }
    //getters
    public String getName() {
        return name;
    }
    public int getPilotSkill() {
        return pilotSkill;
    }
    public int getEngSkill() {
        return engSkill;
    }
    public int getTradeSkill() {
        return tradeSkill;
    }
    public int getFightSkill() {
        return fightSkill;
    }
    public Ship getShip() {
        return ship;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }
    public void setEngSkill(int engSkill) {
        this.engSkill = engSkill;
    }
    public void setTradeSkill(int tradeSkill) {
        this.tradeSkill = tradeSkill;
    }
    public void setFightSkill(int fightSkill) {
        this.fightSkill = fightSkill;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    //toString
    public String toString() {
        return String.format(
                "Name: %s, Pilot Skill: %i, Engineering Skill: %i, Trade Skill: %i, Fighting Skill: %i, Ship: %s",
                name, pilotSkill, engSkill, tradeSkill, fightSkill, ship.toString());
    }


}
