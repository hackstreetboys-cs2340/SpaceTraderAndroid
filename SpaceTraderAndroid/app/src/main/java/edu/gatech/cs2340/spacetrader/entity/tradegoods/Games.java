package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Games extends TradeGood {

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
