package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Universe;

/**
 * Universe Interactor Class
 */
public class UniverseInteractor extends Interactor{
    /**
     * constructor for player interactor
     *
     * @param repo respository
     */
    public UniverseInteractor(Repository repo) {
        super(repo);
    }

    /**
     * setter for player to interact (accesses Repository's setter)
     *
     * @param universe player
     */
    public void setMyUniverse(Universe universe) {
        getRepository().setMyUniverse(universe);
    }

    /**
     * gets the universe for the current player
     * @return universe
     */
    public Universe getMyUniverse() {
        return getRepository().getMyUniverse();
    }
}
