package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.entity.Player;

public class PlayerViewModel extends AndroidViewModel {

    private PlayerInteractor interactor;

    /**
     * constructor for PlayerViewModel
     *
     * @param application app
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * method to add a player to the app
     * @param player player to add
     */
    public void addPlayer(Player player) {
        interactor.setMyPlayer(player);
    }
}
