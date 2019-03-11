package edu.gatech.cs2340.spacetrader.model;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

public class SellGoodInteractor extends Interactor {

    public SellGoodInteractor(Repository repo) {
        super(repo);
    }

    public void addPlayerGood(TradeGood tradeGood) {
        getRepository().addPlayerGood(tradeGood);
    }

    public List<TradeGood> getTradeGoodsForPlayer(Player player) {
        return getRepository().getTradeGoodsForPlayer(player);
    }
}
