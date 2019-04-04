package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

public class PlanetInteractor extends Interactor {

    public PlanetInteractor(Repository repo) {
        super(repo);
    }

    public List<Planet> getPlanetsForSystem(SolarSystem system) {
        return getRepository().getPlanetsForSystem(system);
    }
}
