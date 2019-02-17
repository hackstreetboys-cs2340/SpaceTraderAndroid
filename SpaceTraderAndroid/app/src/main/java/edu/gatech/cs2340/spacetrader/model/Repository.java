package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Ship;

public class Repository {

    private Ship myShip;

    private Player myPlayer;

    public Repository() {
        myPlayer = new Player();
        myShip = new Ship();
    }

    public Player getMyPlayer() {return myPlayer;}
    public Ship getMyShip() {return myShip;}

    public void setMyShip(Ship ship) {this.myShip = ship;}
    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }
}
