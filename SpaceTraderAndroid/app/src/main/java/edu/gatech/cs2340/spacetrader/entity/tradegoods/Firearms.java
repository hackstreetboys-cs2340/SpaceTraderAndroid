package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Firearms Good Class
 */
public class Firearms extends TradeGood {

    /**
     * constructor for firearms
     * numbers based on table given in Canvas
     */
    public Firearms() {
        super("Firearms", 3, 1, 5, 1250, -75, 75);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.Warlike)) {
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
