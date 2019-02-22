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
import edu.gatech.cs2340.spacetrader.viewmodels.PlayerViewModel;

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
    private PlayerViewModel viewModel;

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
        // perhaps if-else if we need to edit later

        player = new Player();

        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        Log.d("Notice", "View created");
    }

    /**
     * Button handler for start game button.
     *
     * @param view the button that was pressed
     */
    public void onStartPressed(View view) {
        Log.d("Notice", "Button clicked");

        player.setName(name.getText().toString());
        player.setEngSkill(Integer.parseInt(engSkill.getText().toString()));
        player.setFightSkill(Integer.parseInt(fightSkill.getText().toString()));
        player.setTradeSkill(Integer.parseInt(tradeSkill.getText().toString()));
        player.setPilotSkill(Integer.parseInt(pilotSkill.getText().toString()));
        player.setDifficulty((Difficulty) difficultySpinner.getSelectedItem());

        Log.d("Notice", "player skills set");

        int totalPointValue = Integer.parseInt(engSkill.getText().toString())
                + Integer.parseInt(fightSkill.getText().toString())
                + Integer.parseInt(tradeSkill.getText().toString())
                + Integer.parseInt(pilotSkill.getText().toString());

        Log.d("Notice", "totalPointValue calculated");


        if (totalPointValue == 16) {
            viewModel.addPlayer(player);
            Log.d("EntityData", "Player info:\n" + player.toString());
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (totalPointValue < 16) {
            Toast toast = Toast.makeText(getApplicationContext(), "You did not allocate all 16 points. Try again!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You can only allocate 16 points. Try again!", Toast.LENGTH_LONG);
            toast.show();
        }
        Log.d("EntityData", "Player info:\n" + player.toString());
    }
}
