package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Universe;

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

    public Universe getMyUniverse() {
        return getRepository().getMyUniverse();
    }
}
