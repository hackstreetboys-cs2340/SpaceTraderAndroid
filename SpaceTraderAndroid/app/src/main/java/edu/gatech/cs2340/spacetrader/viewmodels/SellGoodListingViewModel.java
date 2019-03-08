package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.SellGoodInteractor;

public class SellGoodListingViewModel extends AndroidViewModel {
    private SellGoodInteractor interactor;
    private Player currentPlayer;

    public SellGoodListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSellGoodInteractor();
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public List<TradeGood> getSellTradeGoods() {
        return interactor.getTradeGoodsForPlayer(currentPlayer);
    }
}
