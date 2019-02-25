package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class PlayerInteractor extends Interactor {

    /**
     * constructor for player interactor
     *
     * @param repo respository
     */
    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    /**
     * setter for player to interact (accesses Repository's setter)
     *
     * @param player player
     */
    public void setMyPlayer(Player player) {
        getRepository().setMyPlayer(player);
    }
}
