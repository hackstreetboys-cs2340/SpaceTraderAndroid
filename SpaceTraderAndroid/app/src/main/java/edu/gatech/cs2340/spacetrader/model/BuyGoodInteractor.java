package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

public class BuyGoodInteractor extends Interactor{

    public BuyGoodInteractor(Repository repo) {
        super(repo);
    }

    public void removePlayerGood(TradeGood tradeGood) {
        getRepository().removePlayerGood(tradeGood);
    }

    public List<TradeGood> getTradeGoodsForPlanet(Planet planet) {
        return getRepository().getTradeGoodsForPlanet(planet);
    }
}
