package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

/**
 * Sell Good Interactor Class
 */
public class SellGoodInteractor extends Interactor {

    /**
     * constructor for sell good interactor
     * @param repo repository
     */
    public SellGoodInteractor(Repository repo) {
        super(repo);
    }

    /**
     * adds a good to players cargo
     * @param tradeGood good
     */
    public void addPlayerGood(TradeGood tradeGood) {
        getRepository().addPlayerGood(tradeGood);
    }

    /**
     * get a list of player's goods
     * @param player current player
     * @return list of goods
     */
    public List<TradeGood> getTradeGoodsForPlayer(Player player) {
        return getRepository().getTradeGoodsForPlayer(player);
    }
}
