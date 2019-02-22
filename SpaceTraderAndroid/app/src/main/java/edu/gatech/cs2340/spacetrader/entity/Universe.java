package edu.gatech.cs2340.spacetrader.entity;

import java.util.HashSet;

/**
 * Object that represents the game's universe,
 * and all the solar systems within it.
 */
public class Universe {
    private HashSet<SolarSystem> solarSystems;

    /**
     * No arg constructor initializes solarSystems to an empty HashSet
     */
    public Universe() {
        solarSystems = new HashSet<SolarSystem>();
    }

    /**
     * solarSystems getter
     * @return a list of solar systems in the universe
     */
    public HashSet<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    @Override
    public String toString() {
        String str = "Universe (" + solarSystems.size() + " unique systems):\n";
        for (SolarSystem system : solarSystems) {
            str += system + "\n";
        }
        return str;
    }
}
