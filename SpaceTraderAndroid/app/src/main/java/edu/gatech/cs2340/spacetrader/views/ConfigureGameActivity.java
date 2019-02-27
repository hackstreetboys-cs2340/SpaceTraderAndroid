package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import edu.gatech.cs2340.spacetrader.entity.Player;
import android.view.View;
import edu.gatech.cs2340.spacetrader.entity.Difficulty;
import android.widget.Button;
import android.widget.Toast;

import edu.gatech.cs2340.spacetrader.R;
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
        Log.d("Notice", "Button clicked");

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
                playerViewModel.addPlayer(player);
                Log.d("EntityData", "Player info:\n" + player.toString());
                Universe generatedUniverse = new Universe();
                generatedUniverse.generate();
                universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
                universeViewModel.addUniverse(generatedUniverse);
                Log.d("EntityData", "Universe Info: \n" + generatedUniverse.toString());
                Intent intent = new Intent(ConfigureGameActivity.this, TransitionActivity.class);
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
}
