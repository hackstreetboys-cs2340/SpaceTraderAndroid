package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.viewmodels.BuyGoodListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.SellGoodListingViewModel;

public class TravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Model model = Model.getInstance();
        PlayerInteractor playerInteractor = model.getPlayerInteractor();
        currentPlayer = playerInteractor.getMyPlayer();
        currentPlanet = currentPlayer.getLocation();

        RecyclerView buyRecyclerView = findViewById(R.id.buy_market_list);
        RecyclerView sellRecyclerView = findViewById(R.id.sell_market_list);

        buyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sellRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        buyAdapter = new BuyMarketItemAdapter();
        buyRecyclerView.setAdapter(buyAdapter);
        sellAdapter = new SellMarketItemAdapter();
        sellRecyclerView.setAdapter(sellAdapter);

        buyViewModel = ViewModelProviders.of(this).get(BuyGoodListingViewModel.class);
        buyViewModel.setCurrentPlanet(currentPlanet);
        sellViewModel = ViewModelProviders.of(this).get(SellGoodListingViewModel.class);
        sellViewModel.setCurrentPlayer(currentPlayer);
    }
}
