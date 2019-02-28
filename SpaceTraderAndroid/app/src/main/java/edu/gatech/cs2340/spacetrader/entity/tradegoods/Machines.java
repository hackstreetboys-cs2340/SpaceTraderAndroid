package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Machines extends TradeGood {

    public Machines() {
        super("Machines", 4, 3, 5, 900, -30, 5);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.LackOfWorkers)) {
            finalPrice = basePrice * 2;
            return true;
        } else {
            return false;
        }
    }
}
