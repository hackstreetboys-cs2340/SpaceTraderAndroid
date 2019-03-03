package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Water extends TradeGood{

    public Water() {
        super("Water", 0, 0, 2, 30, 3, 4);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.Desert)) {
            finalPrice = basePrice * 1.3;
            return true;
        } else if (res.equals(Resources.LotsOfWater)) {
            finalPrice = basePrice * .6;
            return true;
        } else if (res.equals(Resources.Drought)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
