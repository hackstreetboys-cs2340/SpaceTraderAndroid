package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.model.BuyGoodInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;

/**
 * Buy Good Listing View Model
 */
public class BuyGoodListingViewModel extends AndroidViewModel {
    private BuyGoodInteractor interactor;
    private Planet currentPlanet;

    /**
     * constructor for buy good listing viewmodel
     * @param application app
     */
    public BuyGoodListingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getBuyGoodInteractor();
    }

    /**
     * set the current planet that the player is on
     * @param planet planet
     */
    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }

    /**
     * remove a good from player
     * @param tradeGood good
     */
    public void removePlayerGood(TradeGood tradeGood) {
        interactor.removePlayerGood(tradeGood);
    }

    /**
     * get available trade goods from planet
     * @return list of goods
     */
    public List<TradeGood> getBuyTradeGoods() {
        return interactor.getTradeGoodsForPlanet(currentPlanet);
    }
}
