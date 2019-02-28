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
    List<TradeGood> tradeGoods;
    TechLevel techLevel;
    Resources resources;

    public Market() {
        this.tradeGoods = new ArrayList<>(10);
    }

    public Market(TechLevel techLevel, Resources resources) {
        this.tradeGoods = new ArrayList<>(10);
        this.techLevel = techLevel;
        this.resources = resources;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

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

    public String toString() {
        String str = "";
        for (TradeGood i : tradeGoods) {
            str += i.toString() + "\n";
        }
        return str;
    }
}
