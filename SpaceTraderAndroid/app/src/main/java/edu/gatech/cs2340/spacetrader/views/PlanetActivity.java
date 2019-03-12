package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;

public class PlanetActivity extends AppCompatActivity {

    private TextView planetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        planetName = findViewById(R.id.planet_name_textView);
        Model model = Model.getInstance();
        PlayerInteractor playerInteractor = model.getPlayerInteractor();
        Player player = playerInteractor.getMyPlayer();
        Planet planet = player.getLocation();
        planetName.setText(planet.getName());
    }

    public void onMarketPressed(View view) {
        Log.d("_test1", "market button pressed");
        Intent intent = new Intent(PlanetActivity.this, MarketActivity.class);
        startActivity(intent);
    }
}
