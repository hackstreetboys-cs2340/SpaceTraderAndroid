package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

/**
 * Buy Good Interactor Class
 */
public class BuyGoodInteractor extends Interactor{

    /**
     * constructor for buy trade good interactor
     * @param repo repository
     */
    public BuyGoodInteractor(Repository repo) {
        super(repo);
    }

    /**
     * removes good from player
     * @param tradeGood good to be removed
     */
    public void removePlayerGood(TradeGood tradeGood) {
        getRepository().removePlayerGood(tradeGood);
    }

    /**
     * for a given planet, give a list of trade goods
     * @param planet planet
     * @return list of trade goods
     */
    public List<TradeGood> getTradeGoodsForPlanet(Planet planet) {
        return getRepository().getTradeGoodsForPlanet(planet);
    }
}
