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
    private TextView planetStats;
    private TextView playerName;
    private TextView playerStats;
    private TextView shipType;
    private TextView shipStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        planetName = findViewById(R.id.planet_name_textView);
        planetStats = findViewById(R.id.planet_stats_textView);
        playerName = findViewById(R.id.player_name_textView);
        playerStats = findViewById(R.id.player_stats_textView);
        shipType = findViewById(R.id.shiptype_textView);
        shipStats = findViewById(R.id.ship_stats_textView);

        Model model = Model.getInstance();
        PlayerInteractor playerInteractor = model.getPlayerInteractor();
        Player player = playerInteractor.getMyPlayer();
        Planet planet = player.getLocation();

        planetName.setText(planet.getName());

        String planStats = "";
        planStats += "Resources : " + planet.getResources() + "\n";
        planStats += "Tech Level : " + planet.getTechLevel();
        planetStats.setText(planStats);

        playerName.setText("Name : " + player.getName());

        String playStats = "";
        String wallet = String.format("%.2f", player.getWallet());
        playStats += "Wallet : $" + wallet;
        playerStats.setText(playStats);

        shipType.setText("Ship : " + player.getShipName());

        String sStats = "";
        sStats += "Health : " + player.getShip().getHealth() + "\n";
        sStats += "Fuel : " + player.getShip().getFuel() + "\n";
        sStats += "Cargo : " + player.getShip().getCargoHold().size() + " / "
                + player.getShip().getCapacity() + "\n";
        shipStats.setText(sStats);


    }

    public void onMarketPressed(View view) {
        Log.d("_test1", "market button pressed");
        Intent intent = new Intent(PlanetActivity.this, MarketActivity.class);
        startActivity(intent);
    }

    public void onMapPressed(View view) {
        Log.d("_test1", "map button pressed");
        Intent intent = new Intent(PlanetActivity.this, TravelActivity.class);
        startActivity(intent);
    }
}
