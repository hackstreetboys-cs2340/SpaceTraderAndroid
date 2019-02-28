package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Narcotics extends TradeGood {

    public Narcotics() {
        super("Narcotics", 5, 0, 5, 3500, -125, 150);
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
