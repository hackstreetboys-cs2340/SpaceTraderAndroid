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
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Robots;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.Water;

/**
 * Market Class
 */
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

    /**
     * get a list of trade goods from the market
     * @return list of goods
     */
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
        tradeGoods.add(new Robots());
        for (TradeGood i : tradeGoods) {
            i.calculatePrice(techLevel, resources);
        }
    }

    public String toString() {
        String str = "";
        for (TradeGood i : tradeGoods) {
            str += i.toString() + "\n";
        }
        return str;
    }
}
