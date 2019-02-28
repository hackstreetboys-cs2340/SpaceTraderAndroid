package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Resources;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;

public abstract class TradeGood {
    private String name;
    private int MTLP, MTLU, TTP, IPL, variance;
    double basePrice, finalPrice;
    public TradeGood() {
        this("", 0, 0, 0, 0, 0, 0);
    }

    public TradeGood(String name) {
        this(name, 0, 0, 0, 0, 0, 0);
    }

    public TradeGood(String name, int MTLP, int MTLU, int TTP, double basePrice, int IPL, int variance) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.variance = variance;
        this.finalPrice = basePrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void calculatePrice(TechLevel tech, Resources res) {
        Random rnd = new Random();
        if (MTLP > tech.getLevel() && MTLU > tech.getLevel()) {
            finalPrice = -3;
        } else if (MTLU > tech.getLevel()) {
            finalPrice = -2;
        } else if (MTLP > tech.getLevel()) {
            finalPrice = -1;
        } else {
            if (!checkConditions(res)) {
                int coin = rnd.nextInt(2);
                int var = rnd.nextInt(variance + 1);
                if (coin == 0) {
                    finalPrice = basePrice + IPL * (tech.getLevel() - MTLP) - (basePrice * (double)(var / 100));
                } else {
                    finalPrice = basePrice + IPL * (tech.getLevel() - MTLP) + (basePrice * (double)(var / 100));
                }
            }
            //by a lot of good fortune sometimes the final price goes negative or to zero
            //this condition sets the price to a penny if that is the case
            if (finalPrice <= 0) {
                finalPrice = .01;
            }
        }
    }

    abstract boolean checkConditions(Resources res);

    public String toString() {
        if (finalPrice == -3) {
            return "You can't buy nor sell " + name + " on this planet";
        } else if (finalPrice == -2) {
            return "You can't sell " + name + " on this planet";
        } else if (finalPrice == -1) {
            return "You can't buy " + name + " on this planet";
        } else {
            return "The price of " + name + " is $" + String.format("%.2f", finalPrice);
        }
    }
}
