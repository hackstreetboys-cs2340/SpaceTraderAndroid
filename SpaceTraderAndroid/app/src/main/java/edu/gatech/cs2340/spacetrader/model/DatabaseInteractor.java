package edu.gatech.cs2340.spacetrader.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cs2340.spacetrader.entity.Player;

public class DatabaseInteractor {
    private DatabaseReference ref;
    public DatabaseInteractor() {
        ref = FirebaseDatabase.getInstance().getReference();
    }
    public void uploadPlayer(Player player, String uid) {
        ref.child("Users").child(uid).setValue(player);
    }
    public Player downloadPlayer(String uid) {
        return null;
    }
}
