package edu.gatech.cs2340.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.tradegoods.Firearms;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Food;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Furs;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Games;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Machines;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Medicine;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Narcotics;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Ore;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Water;

public class Market {
    private List<TradeGood> tradeGoods;
    private TechLevel techLevel;
    private Resources resources;

    /**
     * no args constructor
     */
    public Market() {
        this.tradeGoods = new ArrayList<>(10);
    }

    /**
     * full constructor
     * @param techLevel planet tech level
     * @param resources planet resources
     */
    public Market(TechLevel techLevel, Resources resources) {
        this.tradeGoods = new ArrayList<>(10);
        this.techLevel = techLevel;
        this.resources = resources;
    }

    /**
     * set the tech level
      * @param techLevel planet tech level
     */
    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * set the resources
     * @param resources plant resources
     */
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public List<TradeGood> getTradeGoods() {
        return tradeGoods;
    }

    /**
     * generates a market
     */
    public void generateMarket() {
        tradeGoods.add(new Water());
        tradeGoods.add(new Furs());
        tradeGoods.add(new Food());
        tradeGoods.add(new Ore());
        tradeGoods.add(new Games());
        tradeGoods.add(new Firearms());
        tradeGoods.add(new Medicine());
        tradeGoods.add(new Machines());
        tradeGoods.add(new Narcotics());
        for (TradeGood i : tradeGoods) {
            i.calculatePrice(techLevel, resources);
        }
    }

    /**
     * removed the good from the planet
     * @param good good to be sold
     * @return price of the good
     */
    public double sell(TradeGood good) {
        double price = tradeGoods.get(tradeGoods.indexOf(good)).getFinalPrice();
        tradeGoods.remove(good);
        return price;
    }

    /**
     * add the good to the planet
     * @param good good to be bought
     * @return price of the good
     */
    public double buy(TradeGood good) {
        tradeGoods.add(good);
        return good.getFinalPrice();
    }

    public String toString() {
        String str = "";
        for (TradeGood i : tradeGoods) {
            str += i.toString() + "\n";
        }
        return str;
    }
}
