package edu.gatech.cs2340.spacetrader.entity;

import java.util.Random;

/**
 * Planet class allows Planet to be an instance class
 * Allows the player to jump to different planets which are a part of different
 * Solar Systems which are a part of different Universes
 */
public class Planet {
    private String name;
    private TechLevel techLevel;
    private Resources resource;

    /**
     * No-arg constructor for the Planet class that uses constructor chaining
     * to set default values for Name, Coordinates, TechLevel, and Resources
     *
     */
    public Planet() {
        this("", TechLevel.PreAgriculture, Resources.NoSpecialResources);

    }

    public Planet(String name) {
        this(name, TechLevel.PreAgriculture, Resources.NoSpecialResources);
    }

    /**
     * Second constructor for the Planet class that allows the Planet to be instantiated
     * by the Player.
     * @param name represents the name of the planet
     * @param techLevel represents the tech level of the planet
     * @param resource represents the resource that the planet is known for
     */
    public Planet(String name, TechLevel techLevel, Resources resource) {
        this.name = name;
        this.techLevel = techLevel;
        this.resource = resource;
    }

    /**
     * Setter method for the Planet name
     * @param name represents the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the Planet name
     * @return name of the Planet
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for tech level of planet
     * @param techLevel represents the tech level of the planet
     */
    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * Getter method for tech level of planet
     * @return techLevel of the planet
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Setter method for resource of a planet
     * @param resource represents the resource that the planet is known for
     */
    public void setResources(Resources resource) {
        this.resource = resource;
    }

    /**
     * Getter method for the resource of a planet
     * @return resource of the planet
     */
    public Resources getResources() {
        return resource;
    }

    /**
     * assigns a random resource level and tech level
     */

    public void generate() {
        this.resource = Resources.getRandomResources();
        this.techLevel = TechLevel.getRandomTech();
    }
}
