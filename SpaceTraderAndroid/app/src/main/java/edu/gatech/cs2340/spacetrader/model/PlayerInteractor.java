package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;

/**
 * Player Interactor Class
 */
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

    /**
     * get the current player
     * @return the current player
     */
    public Player getMyPlayer() { return getRepository().getMyPlayer(); }
}
