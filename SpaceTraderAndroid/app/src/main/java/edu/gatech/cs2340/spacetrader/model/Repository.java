package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Ship;

public class Repository {

    private Ship myShip;

    private Player myPlayer;

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
     * getter method for ship
     *
     * @return ship
     */
    public Ship getMyShip() {return myShip;}

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
}
