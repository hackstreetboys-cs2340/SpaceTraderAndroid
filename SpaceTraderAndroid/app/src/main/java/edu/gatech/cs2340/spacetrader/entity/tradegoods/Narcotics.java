package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Narcotics Good Class
 */
public class Narcotics extends TradeGood {

    /**
     * constructor for narcotics
     * numbers based on table given in Canvas
     */
    public Narcotics() {
        super("Narcotics", 5, 0, 5, 3500, -125, 75);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.WeirdMushrooms)) {
            finalPrice = basePrice * .6;
            return true;
        } else if (res.equals(Resources.Boredom)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
