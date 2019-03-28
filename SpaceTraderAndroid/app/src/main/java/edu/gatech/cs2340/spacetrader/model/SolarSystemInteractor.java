package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;

public class SolarSystemInteractor extends Interactor {

    public SolarSystemInteractor(Repository repo) {
        super(repo);
    }

    public List<SolarSystem> getSystemsForUniverse(Universe universe) {
        return getRepository().getSolarSystemsForUniverse(universe);
    }
}
