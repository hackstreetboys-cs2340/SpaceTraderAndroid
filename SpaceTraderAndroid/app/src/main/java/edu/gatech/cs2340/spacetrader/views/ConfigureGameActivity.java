package edu.gatech.cs2340.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import edu.gatech.cs2340.spacetrader.entity.Player;

import edu.gatech.cs2340.spacetrader.R;

public class ConfigureGameActivity extends AppCompatActivity {

    private Spinner difficultySpinner;
    private EditText name;
    private EditText pilotSkill;
    private EditText engSkill;
    private EditText tradeSkill;
    private EditText fightSkill;

    private Player player;


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

        ArrayAdapter<edu.gatech.cs2340.spacetrader.entity.Difficulty> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, edu.gatech.cs2340.spacetrader.entity.Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        // add new player
        // perhaps if-else if we need to edit later

        player = new Player("Player 1");
        name.setText(player.getName());
        pilotSkill.setText(player.getPilotSkill());
        engSkill.setText(player.getPilotSkill());
        tradeSkill.setText(player.getTradeSkill());
        fightSkill.setText(player.getFightSkill());
        difficultySpinner.setSelection(player.getDifficulty().ordinal());



    }
}
