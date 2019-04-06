package edu.gatech.cs2340.spacetrader.entity;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarSystem {

    private String name;
    private Pair<Integer, Integer> coordinates;
    private List<Planet> planets;

    /**
     * no-arg constructor for SolarSystem
     */
    public SolarSystem(){
        this("");
    }

    public SolarSystem(String name) {
        this(name, new Pair<>(0, 0), new ArrayList<Planet>());
    }

    public SolarSystem(String name, Pair coordinates) {
        this(name, coordinates, new ArrayList<Planet>());
    }

    /**
     * all-arguments constructor for SolarSystem
     *
     * @param name name of solar system
     * @param coordinates coordinates of solar system
     * @param planets list of planets
     */
    public SolarSystem(String name, Pair coordinates, List<Planet> planets){
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
    public Pair getCoordinates() {
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
    public void setCoordinates(Pair coordinates) {
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

    /**
     * populates the system with planets
     * @param numOfPlanets number of planets to be added
     * @param planetNames list of possible planet names
     */

    public void generateSystem(int numOfPlanets, List<String> planetNames) {
        Random rnd = new Random();
        while (numOfPlanets > 0) {
            Planet newPlanet = new Planet(planetNames.remove(rnd.nextInt(planetNames.size())), this.coordinates);
            newPlanet.generate();
            planets.add(newPlanet);
            numOfPlanets--;
        }
    }

    @Override
    public String toString() {
        String str = "Name: " + name + "\nCoordinates: (" + coordinates.first + ", " + coordinates.second + ")\n" + planets.size() + " Planet(s):\n";
        for (Planet pl : planets) {
            str += pl + "\n";
        }
        return str;
    }
}
