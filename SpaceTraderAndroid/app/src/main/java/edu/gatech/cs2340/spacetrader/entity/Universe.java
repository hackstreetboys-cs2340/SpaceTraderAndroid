package edu.gatech.cs2340.spacetrader.entity;

import java.util.HashSet;

/**
 * Object that represents the game's universe,
 * and all the solar systems within it.
 */
public class Universe {
    HashSet<SolarSystem> solarSystems;

    /**
     * No arg constructor initializes solarSystems to an empty HashSet
     */
    public Universe() {
        solarSystems = new HashSet<SolarSystem>();
    }
}
