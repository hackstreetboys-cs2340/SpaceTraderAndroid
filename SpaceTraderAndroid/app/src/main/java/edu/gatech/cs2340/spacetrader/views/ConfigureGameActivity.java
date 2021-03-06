package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import android.view.View;
import edu.gatech.cs2340.spacetrader.entity.Difficulty;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.UniverseViewModel;

/**
 * Code behind adding a player and allocating points.
 */
public class ConfigureGameActivity extends AppCompatActivity {

    private Spinner difficultySpinner;
    private EditText name;
    private EditText pilotSkill;
    private EditText engSkill;
    private EditText tradeSkill;
    private EditText fightSkill;
    private Player player;
    private PlayerViewModel playerViewModel;
    private UniverseViewModel universeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_game);

        name = findViewById(R.id.name_input);
        pilotSkill = findViewById(R.id.pilot_points);
        engSkill = findViewById(R.id.engineer_points);
        tradeSkill = findViewById(R.id.trade_points);
        fightSkill = findViewById(R.id.fighter_points);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        Button button = findViewById(R.id.start_game);

        ArrayAdapter<edu.gatech.cs2340.spacetrader.entity.Difficulty> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, edu.gatech.cs2340.spacetrader.entity.Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        // add new player

        player = new Player();

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        Log.d("Notice", "View created");
    }

    /**
     * Button handler for start game button.
     *
     * @param view the button that was pressed
     */
    public void onStartPressed(View view) {

        if (name.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "You must set a player name", Toast.LENGTH_LONG);
            toast.show();

        } else {
            player.setName(name.getText().toString());
            if (engSkill.getText().toString().equals("")) {
                player.setEngSkill(0);
            } else {
                player.setEngSkill(Integer.parseInt(engSkill.getText().toString()));
            }
            if (fightSkill.getText().toString().equals("")) {
                player.setFightSkill(0);
            } else {
                player.setFightSkill(Integer.parseInt(fightSkill.getText().toString()));
            }
            if (tradeSkill.getText().toString().equals("")) {
                player.setTradeSkill(0);
            } else {
                player.setTradeSkill(Integer.parseInt(tradeSkill.getText().toString()));
            }
            if (pilotSkill.getText().toString().equals("")) {
                player.setPilotSkill(0);
            } else {
                player.setPilotSkill(Integer.parseInt(pilotSkill.getText().toString()));
            }
            player.setDifficulty((Difficulty) difficultySpinner.getSelectedItem());

            Log.d("Notice", "player skills set");

            int totalPointValue = player.getEngSkill()
                    + player.getFightSkill()
                    + player.getTradeSkill()
                    + player.getPilotSkill();

            Log.d("Notice", "totalPointValue calculated");


            if (totalPointValue == 16) {
                Log.d("EntityData", "Player info:\n" + player.toString());
                Random rand = new Random();
                long seed = rand.nextLong();
                player.setSeed(seed);
                Universe generatedUniverse = new Universe();
                generatedUniverse.generate(seed);
                universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
                universeViewModel.addUniverse(generatedUniverse);
                largeLog("EntityData", "Universe Info: \n" + generatedUniverse.toString());

                // choose a random planet for the player to start on
                boolean hasPlanets = false;
                while (!hasPlanets) {
                    int numSystems = generatedUniverse.getSolarSystems().size()-1;
                    int randSystem = rand.nextInt(numSystems);
                    SolarSystem currSystem = generatedUniverse.getSolarSystems().get(randSystem);
                    if (currSystem.getPlanets().size() == 1) {
                        player.setLocation(currSystem.getPlanets().get(0));
                        hasPlanets = true;
                    } else if (currSystem.getPlanets().size() != 0) {
                        int randPlanet = rand.nextInt(currSystem.getPlanets().size());
                        Planet currPlanet = currSystem.getPlanets().get(randPlanet);
                        player.setLocation(currPlanet);
                        hasPlanets = true;
                    }
                }
                String uid = FirebaseAuth.getInstance().getUid();
                playerViewModel.addPlayer(player);
                playerViewModel.uploadPlayer(player, uid);
                Intent intent = new Intent(ConfigureGameActivity.this, PlanetActivity.class);
                startActivity(intent);

            } else if (totalPointValue < 16) {
                Toast toast = Toast.makeText(getApplicationContext(), "You did not allocate all 16 points. Try again!", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You can only allocate 16 points. Try again!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        Log.d("EntityData", "Player info:\n" + player.toString());


    }

    /**
     * handles a large log
     * @param tag tag for log
     * @param content message for log
     */
    private static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }
}
