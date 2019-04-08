package edu.gatech.cs2340.spacetrader.entity;

import java.util.Random;

/**
 * Tech Levels for planet
 */
public enum TechLevel {
    PreAgriculture (0),
    Agriculture (1),
    Medieval (2),
    Renaissance (3),
    EarlyIndustrial (4),
    Industrial (5),
    PostIndustrial (6),
    HiTech (7);

    private final int level;

    /**
     * enum constructor for TechLevel
     *
     * @param level tech level
     */
    TechLevel (int level) {
        this.level = level;
    }

    /**
     * getter method for tech level
     *
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * generates a random tech level
     *
     * @return random tech level
     */

    public static TechLevel getRandomTech() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return String.valueOf(this.level);
    }

}
