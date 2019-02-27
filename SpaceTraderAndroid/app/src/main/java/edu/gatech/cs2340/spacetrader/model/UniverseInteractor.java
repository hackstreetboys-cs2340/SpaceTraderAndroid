package edu.gatech.cs2340.spacetrader.model;

import edu.gatech.cs2340.spacetrader.entity.Universe;

public class UniverseInteractor extends Interactor {

    /**
     * constructor for universe interactor
     *
     * @param repo respository
     */
    public UniverseInteractor(Repository repo) {
        super(repo);
    }

    /**
     * setter for universe to interact (accesses Repository's setter)
     * javadoc
     *
     * @param universe universe
     */
    public void setMyUniverse(Universe universe) {
        getRepository().setMyUniverse(universe);
    }
}