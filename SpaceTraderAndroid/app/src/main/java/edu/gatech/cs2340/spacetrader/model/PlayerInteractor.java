package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public void setMyPlayer(Player player) {
        getRepository().setMyPlayer(player);
    }
}
