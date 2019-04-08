package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Ore Good Class
 */
public class Ore extends TradeGood {

    /**
     * constructor for ore
     * numbers based on table given in Canvas
     */
    public Ore() {
        super("Ore", 2, 2, 3, 350, 20, 10);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.MineralPoor)) {
            finalPrice = basePrice * 1.3;
            return true;
        } else if (res.equals(Resources.MineralRich)) {
            finalPrice = basePrice * .6;
            return true;
        } else if (res.equals(Resources.War)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
