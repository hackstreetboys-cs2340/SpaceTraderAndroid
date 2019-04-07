package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Furs Good Class
 */
public class Furs extends TradeGood {

    /**
     * constructor for fur
     * numbers based on table given in Canvas
     */
    public Furs () {
        super("Furs", 0, 0, 0, 250, 10, 10);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.Lifeless)) {
            finalPrice = basePrice * 1.3;
            return true;
        } else if (res.equals(Resources.RichFauna)) {
            finalPrice = basePrice * .6;
            return true;
        } else if (res.equals(Resources.Cold)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
