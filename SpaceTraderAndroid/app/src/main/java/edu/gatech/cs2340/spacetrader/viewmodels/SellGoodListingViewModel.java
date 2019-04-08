package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SellGoodInteractor;

/**
 * Sell Good Listing View Model Class
 */
public class SellGoodListingViewModel extends AndroidViewModel {
    private SellGoodInteractor interactor;
    private Player currentPlayer;

    /**
     * constructor for sell good listing view model
     * @param application app
     */
    public SellGoodListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSellGoodInteractor();
    }

    /**
     * sets the current player
     * @param player player
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     * add a good to player's cargo
     * @param tradeGood good to be added
     */
    public void addPlayerGood(TradeGood tradeGood) {
        interactor.addPlayerGood(tradeGood);
    }

    /**
     * get the the goods the players can sell
     * @return list of goods
     */
    public List<TradeGood> getSellTradeGoods() {
        return interactor.getTradeGoodsForPlayer(currentPlayer);
    }
}
