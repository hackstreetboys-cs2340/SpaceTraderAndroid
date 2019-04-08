package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.model.BuyGoodInteractor;
import edu.gatech.cs2340.spacetrader.model.DatabaseInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;

public class BuyGoodListingViewModel extends AndroidViewModel {
    private BuyGoodInteractor interactor;
    private DatabaseInteractor databaseInteractor;
    private Planet currentPlanet;

    public BuyGoodListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getBuyGoodInteractor();
        databaseInteractor = Model.getInstance().getDatabaseInteractor();
    }

    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }

    public void removePlayerGood(TradeGood tradeGood) {
        interactor.removePlayerGood(tradeGood);
        databaseInteractor.uploadPlayer();
    }

    public List<TradeGood> getBuyTradeGoods() {
        return interactor.getTradeGoodsForPlanet(currentPlanet);
    }
}
