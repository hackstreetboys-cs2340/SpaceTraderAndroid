package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

/**
 * Solar System Interactor Class
 */
public class SolarSystemInteractor extends Interactor {

    /**
     * constructor for solar system interactor
     * @param repo repository
     */
    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    /**
     * get a list of solar systems for a universe
     * @param universe given universe
     * @return list of solar systems
     */
    public List<SolarSystem> getSystemsForUniverse(Universe universe) {
        return getRepository().getSolarSystemsForUniverse(universe);
    }
}
