package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Medicine Good Class
 */
public class Medicine extends TradeGood {

    /**
     * constructor for medicine
     * numbers based on table given in Canvas
     */
    public Medicine() {
        super("Medicine", 4, 1, 6, 650, -20, 10);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.LotsOfHerbs)) {
            finalPrice = basePrice * .6;
            return true;
        } else if (res.equals(Resources.Plague)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
