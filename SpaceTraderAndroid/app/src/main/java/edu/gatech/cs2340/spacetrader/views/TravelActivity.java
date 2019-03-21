package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.viewmodels.BuyGoodListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.SellGoodListingViewModel;

public class TravelActivity extends AppCompatActivity {

    private SolarSystemItemAdapter solarSystemAdapter;
    private PlanetItemAdapter planetAdapter;
    private Planet currentPlanet;
    private Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Model model = Model.getInstance();
        PlayerInteractor playerInteractor = model.getPlayerInteractor();
        currentPlayer = playerInteractor.getMyPlayer();
        currentPlanet = currentPlayer.getLocation();

        RecyclerView solarSystemRecyclerView = findViewById(R.id.solar_systems_list);
        RecyclerView planetsRecyclerView = findViewById(R.id.planets_list);

        solarSystemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        planetsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        solarSystemAdapter = new SolarSystemItemAdapter();
        solarSystemRecyclerView.setAdapter(solarSystemAdapter);
        planetAdapter = new PlanetItemAdapter();
        planetsRecyclerView.setAdapter(planetAdapter);
    }
}
