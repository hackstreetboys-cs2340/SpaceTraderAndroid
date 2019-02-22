package edu.gatech.cs2340.spacetrader.entity;

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

    TechLevel (int level) {
        this.level = level;
    }

}
