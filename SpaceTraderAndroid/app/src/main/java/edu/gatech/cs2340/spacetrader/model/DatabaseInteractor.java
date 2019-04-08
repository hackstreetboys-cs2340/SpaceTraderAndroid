package edu.gatech.cs2340.spacetrader.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Repo;

import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.spacetrader.entity.Difficulty;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.Ship;
import edu.gatech.cs2340.spacetrader.entity.ShipType;

public class DatabaseInteractor extends Interactor {
    private DatabaseReference ref;
    public DatabaseInteractor(Repository repo) {
        super(repo);
        ref = FirebaseDatabase.getInstance().getReference();
    }
    public void uploadPlayer() {
        Player player = getRepository().getMyPlayer();
        String uid = getRepository().getUID();
        ref.child("Users").child(uid).setValue(player);
    }
    public void downloadPlayer(final ResultHandler<Player> handler) {
        Log.d("TESTING", "Attempting to download");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TESTING", "Data changed");
                if (dataSnapshot.exists()) {
                    Log.d("TESTING", "snapshot exists");
                    /*
                    HashMap<String, Object> data = (HashMap<String, Object>)dataSnapshot.getValue();
                    Player player = new Player();
                    player.setSeed((long)data.get("seed"));
                    player.setEngSkill((int)data.get("engSkill"));
                    player.setFightSkill((int)data.get("fightSkill"));
                    player.setPilotSkill((int)data.get("pilotSkill"));
                    player.setTradeSkill((int)data.get("tradeSkill"));
                    player.setName((String)data.get("name"));
                    player.setWallet((int)data.get("wallet"));
                    player.setDifficulty(Difficulty.valueOf((String)data.get("difficulty")));
                    Ship ship = new Ship();
                    switch ((String)((HashMap<String, Object>)data.get("ship")).get("name")) {
                        case "Gnat":
                            ship = new Ship(ShipType.Gnat);
                            break;
                        default:
                            break;
                    }
                    List<HashMap<Integer,Object>> hold = (List<HashMap<Integer, Object>>)data.get("cargoHold");
                    for (HashMap<Integer, Object> i : hold) {

                    }
                    */


                    Player player = dataSnapshot.getValue(Player.class);
                    //Player player = new Player("JoeSchmo");
                    handler.onSuccess(player);
                } else {
                    handler.onSuccess(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                handler.onFailure(databaseError.toException());
            }
        };
        String uid = getRepository().getUID();
        ref.child("Users").child(uid).addListenerForSingleValueEvent(listener);
    }

    public void setUID(String uid) {
        getRepository().setUID(uid);
    }
}
