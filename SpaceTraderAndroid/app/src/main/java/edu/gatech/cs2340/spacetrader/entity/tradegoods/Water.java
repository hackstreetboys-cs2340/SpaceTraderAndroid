package edu.gatech.cs2340.spacetrader.entity.tradegoods;


import edu.gatech.cs2340.spacetrader.entity.Resources;

public class Water extends TradeGood{

    public Water() {
        super("Water", 0, 0, 2, 30, 3, 4, 30);
    }

    @Override
    boolean checkConditions(Resources res) {
        if (res.equals(Resources.Desert)) {
            return true;
        } else if (res.equals(Resources.LotsOfWater)) {
            return true;
        } else if (res.equals(Resources.Drought)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void generateConditions(Resources res) {
        if (res.equals(Resources.Desert)) {
            setFinalPrice(30 * 1.3);
        } else if (res.equals(Resources.LotsOfWater)) {
            setFinalPrice(30 * .6);
        } else if (res.equals(Resources.Drought)) {
            setFinalPrice(30 * 2);
        }
    }
}
