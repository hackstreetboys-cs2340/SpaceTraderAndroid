package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

/**
 * Planet Interactor
 */
public class PlanetInteractor extends Interactor {

    /**
     * planet interactor constructor
     * @param repo repository
     */
    public PlanetInteractor(Repository repo) {
        super(repo);
    }

    /**
     * get a list of planets for a given system
     * @param system solar system
     * @return list of planets
     */
    public List<Planet> getPlanetsForSystem(SolarSystem system) {
        return getRepository().getPlanetsForSystem(system);
    }
}
