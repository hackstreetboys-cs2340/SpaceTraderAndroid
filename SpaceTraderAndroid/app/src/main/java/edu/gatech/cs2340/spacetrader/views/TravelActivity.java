package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.entity.Universe;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.viewmodels.BuyGoodListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.PlanetListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.SellGoodListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.SolarSystemListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.UniverseViewModel;

public class TravelActivity extends AppCompatActivity {

    private SolarSystemListingViewModel systemViewModel;
    private SolarSystemItemAdapter solarSystemAdapter;
    private PlanetListingViewModel planetViewModel;
    private PlanetItemAdapter planetAdapter;
    private UniverseViewModel universeViewModel;
    private Universe currentUniverse;
    private SolarSystem currentSystem;
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
        solarSystemAdapter.setCurrPlanet(currentPlanet);
        solarSystemRecyclerView.setAdapter(solarSystemAdapter);
        planetAdapter = new PlanetItemAdapter();
        planetsRecyclerView.setAdapter(planetAdapter);

        universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        systemViewModel = ViewModelProviders.of(this).get(SolarSystemListingViewModel.class);
        systemViewModel.setCurrentUniverse(universeViewModel.getMyUniverse());

        planetViewModel = ViewModelProviders.of(this).get(PlanetListingViewModel.class);

        TextView distance = findViewById(R.id.travel_info_textview);

        distance.setText("Distance from " + currentPlanet.getName());

    }

    @Override
    public void onResume() {
        super.onResume();
        solarSystemAdapter.setSystems(systemViewModel.getSystems());

        solarSystemAdapter.setOnSolarSystemClickListener(new SolarSystemItemAdapter.OnSolarSystemClickListener() {
            @Override
            public void onSolarSystemClicked(SolarSystem system) {
                planetViewModel.setCurrentSystem(system);
                planetAdapter.setPlanets(planetViewModel.getPlanets());
                TextView distance = findViewById(R.id.travel_info_textview);
                distance.setText("Distance from " + currentPlanet.getName() + ": " + currentPlanet.distanceTo(system.getCoordinates()));
            }
        });

        planetAdapter.setOnPlanetClickListener(new PlanetItemAdapter.OnPlanetClickListener() {
            @Override
            public void onPlanetClicked(Planet planet) {
                currentPlayer.setLocation(planet);
                Intent intent = new Intent(TravelActivity.this, PlanetActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
