package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Robots Good Class
 */
public class Robots extends TradeGood {
    /**
     * constructor for robots
     * numbers based on table given in Canvas
     */
    public Robots() {
        super("Robots", 6, 4, 7, 5000, -150, 100);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.LackOfWorkers)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
