package edu.gatech.cs2340.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {

    private String name;
    private Coordinates coordinates;
    private List<Planet> planets;

    /**
     * no-arg constructor for SolarSystem
     */
    public SolarSystem(){
        this("", new Coordinates(), new ArrayList<Planet>());
    }

    /**
     * all-arguments constructor for SolarSystem
     *
     * @param name name of solar system
     * @param coordinates coordinates of solar system
     * @param planets list of planets
     */
    public SolarSystem(String name, Coordinates coordinates, List<Planet> planets){
        this.name = name;
        this.coordinates = coordinates;
        this.planets = planets;
    }

    /**
     * getter method for name
     *
     * @return solar system's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter method for coordinates
     *
     * @return solar system's coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * getter method for planets
     *
     * @return list of planets in solar system
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * setter method for name
     *
     * @param name new name of solar system
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method for coordinates
     *
     * @param coordinates new coordinates for solar system
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * setter method for planets in solar system
     *
     * @param planets new list of planets for solar system
     */
    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
