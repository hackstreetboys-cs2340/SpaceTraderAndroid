package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Food Good Class
 */
public class Food extends TradeGood {

    /**
     * constructor for food
     * numbers based on table given in Canvas
     */
    public Food () {
        super("Food", 1, 0, 1, 100, 5, 5);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.PoorSoil)) {
            finalPrice = basePrice * 1.3;
            return true;
        } else if (res.equals(Resources.RichSoil)) {
            finalPrice = basePrice * .6;
            return true;
        } else if (res.equals(Resources.CropFail)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
