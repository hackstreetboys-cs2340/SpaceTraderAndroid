package edu.gatech.cs2340.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.model.DatabaseInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.entity.Player;

/**
 * Player View Model Class
 */
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

    /**
     * Uploads player to database.
     * @param player player of game
     * @param uid user id of player
     */
    public void uploadPlayer(Player player, String uid) { databaseInteractor.uploadPlayer(player, uid); }
}
