package edu.gatech.cs2340.spacetrader.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cs2340.spacetrader.entity.Player;

/**
 * Database Interactor class.
 */
public class DatabaseInteractor {

    private DatabaseReference ref;

    /**
     * Constructor for interactor.
     */
    public DatabaseInteractor() {
        ref = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Uploads player data to database.
     *
     * @param player player of game
     * @param uid user id of player
     */
    public void uploadPlayer(Player player, String uid) {
        ref.child("Users").child(uid).setValue(player);
    }

    /**
     * Downloads player data to device.
     *
     * @param uid user id of player
     * @return player of game
     */
    public Player downloadPlayer(String uid) {
        return null;
    }
}
