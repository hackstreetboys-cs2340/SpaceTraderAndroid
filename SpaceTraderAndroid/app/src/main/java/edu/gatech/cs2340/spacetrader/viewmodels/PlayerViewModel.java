package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.model.DatabaseInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.ResultHandler;

public class PlayerViewModel extends AndroidViewModel {

    private PlayerInteractor interactor;

    private DatabaseInteractor databaseInteractor;

    /**
     * constructor for PlayerViewModel
     *
     * @param application app
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
        databaseInteractor = Model.getInstance().getDatabaseInteractor();
    }

    /**
     * method to add a player to the app
     * @param player player to add
     */
    public void addPlayer(Player player) {
        interactor.setMyPlayer(player);
    }
    public void uploadPlayer() { databaseInteractor.uploadPlayer(); }
    public void setUID(String uid) { databaseInteractor.setUID(uid); }
    public void downloadPlayer(ResultHandler<Player> handler) {
        databaseInteractor.downloadPlayer(handler);
    }
    public Player getPlayer() {
        return interactor.getMyPlayer();
    }
    public boolean travel(Planet planet) {
        boolean canTravel = interactor.travel(planet);
        if (canTravel) {
            databaseInteractor.uploadPlayer();
            return true;
        } else {
            return false;
        }
    }
}
