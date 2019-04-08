package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

/**
 * Games Good Class
 */
public class Games extends TradeGood {

    /**
     * constructor for games
     * numbers based on table given in Canvas
     */
    public Games() {
        super("Games", 3, 1, 6, 250, -10, 5);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.Artistic)) {
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
