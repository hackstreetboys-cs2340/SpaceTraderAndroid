package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.viewmodels.BuyGoodListingViewModel;

public class MarketActivity extends AppCompatActivity {

    private BuyGoodListingViewModel viewModel;
    private BuyMarketItemAdapter buyAdapter;
    private Planet currentPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        //seems redundant to do this since it is done in the Planet Activity but IDK how else to do it
        Model model = Model.getInstance();
        PlayerInteractor playerInteractor = model.getPlayerInteractor();
        Player player = playerInteractor.getMyPlayer();
        currentPlanet = player.getLocation();

        RecyclerView buyRecyclerView = findViewById(R.id.buy_market_list);
        //RecyclerView sellRecyclerView = findViewById(R.id.sell_market_list);

        buyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //sellRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        buyAdapter = new BuyMarketItemAdapter();
        buyRecyclerView.setAdapter(buyAdapter);
        //sellRecyclerView.setAdapter(buyAdapter);

        viewModel = ViewModelProviders.of(this).get(BuyGoodListingViewModel.class);
        viewModel.setCurrentPlanet(currentPlanet);

        TextView buyLabel = findViewById(R.id.buy_textview);
        TextView sellLabel = findViewById(R.id.sell_textview);
        TextView walletLabel = findViewById(R.id.wallet_textview);

        buyLabel.setText("Buy");
        sellLabel.setText("Sell");
        walletLabel.setText("Wallet: $" + String.format("%.2f", player.getWallet()));
    }

    @Override
    public void onResume() {
        super.onResume();
        buyAdapter.setGoods(viewModel.getBuyTradeGoods());
    }

}
