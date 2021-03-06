package edu.gatech.cs2340.spacetrader.entity.tradegoods;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.entity.Resources;
import edu.gatech.cs2340.spacetrader.entity.TechLevel;

/**
 * Abstract Class for TradeGoods
 */
public abstract class TradeGood {
    private String name;
    private int MTLP;
    private int MTLU;
    private int IPL;
    private int variance;
    private int quantity;
    double basePrice, finalPrice;

    /**
     * no args constructor
     */
    public TradeGood() {
        this("", 0, 0, 0, 0, 0, 0);
    }

    /**
     * full constructor
     * @param name good name
     * @param MTLP Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
     * @param MTLU Minimum Tech Level to Use this resource (You can't sell on planets below this level)
     * @param TTP Tech Level which produces the most of this item
     * @param basePrice base price of good
     * @param IPL Price increase per tech level
     * @param variance variance is the maximum percentage that the price can vary above or below the base
     */
    public TradeGood(String name, int MTLP, int MTLU, int TTP, double basePrice, int IPL, int variance) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.variance = variance;
        this.finalPrice = basePrice;
        quantity = 0;
    }

    /**
     * get name
     * @return name of good
     */
    public String getName() {
        return name;
    }

    /**
     * get final price
     * @return final price after adjustments
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * gets the number of a trade good
     * @return amount of trade good
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set the quantity of the trade good
     * @param q amount of trade good
     */
    public void setQuantity(int q) {
        this.quantity = q;
    }

    /**
     * calculates price given resources and tech
     * @param tech tech level of planet
     * @param res resources of planet
     */
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
            /*
            //by a lot of good fortune sometimes the final price goes negative or to zero
            //this condition sets the price to a penny if that is the case
            if (finalPrice <= 0) {
                finalPrice = .01;
            }
            */
        }
    }

    /**
     * check resource conditions
     * @param res resources of planet
     * @return true if final price changed
     */
    abstract boolean checkConditions(Resources res);

    /**
     * Setter for price (used for testing).
     * @param price price to set
     */
    public void setFinalPrice(double price) {
        this.finalPrice = price;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (getClass() != o.getClass()) {
            return false;
        } else if (o == null) {
            return false;
        } else {
            TradeGood good = (TradeGood) o;
            return good.getName().equals(getName());
        }
    }
}
