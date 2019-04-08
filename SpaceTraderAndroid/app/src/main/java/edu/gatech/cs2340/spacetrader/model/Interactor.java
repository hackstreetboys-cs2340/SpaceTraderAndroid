package edu.gatech.cs2340.spacetrader.model;

/**
 * Interactor class
 */
public abstract class Interactor {

    private Repository myRepository;

    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    protected Repository getRepository() {
        return myRepository;
    }
}
