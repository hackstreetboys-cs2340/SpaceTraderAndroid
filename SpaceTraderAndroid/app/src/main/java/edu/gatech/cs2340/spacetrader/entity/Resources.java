package edu.gatech.cs2340.spacetrader.entity;

public enum Resources {
    NoSpecialResources(0),
    MineralRich(1),
    MineralPoor(2),
    Desert(3),
    LotsOfWater(4),
    RichSoil(5),
    PoorSoil(6),
    RichFauna(7),
    Lifeless(8),
    WeirdMushrooms(9),
    LotsOfHerbs(10),
    Artistic(11),
    Warlike(12);

    private final int level;

    /**
     * Constructor method for resource level
     *
     * @param level level of resources
     */
    Resources(int level) {
        this.level = level;
    }

    /**
     * getter method for resource level
     *
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    @Override
    public String toString() {
        return String.valueOf(level);
    }
}
