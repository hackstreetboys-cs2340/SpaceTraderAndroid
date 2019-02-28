package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Firearms extends TradeGood {

    public Firearms() {
        super("Firearms", 3, 1, 5, 1250, -75, 100);
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
