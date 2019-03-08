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
import edu.gatech.cs2340.spacetrader.viewmodels.SellGoodListingViewModel;

public class MarketActivity extends AppCompatActivity {

    private BuyGoodListingViewModel buyViewModel;
    private BuyMarketItemAdapter buyAdapter;
    private SellGoodListingViewModel sellViewModel;
    private SellMarketItemAdapter sellAdapter;
    private Planet currentPlanet;
    private Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        //seems redundant to do this since it is done in the Planet Activity but IDK how else to do it
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



        TextView buyLabel = findViewById(R.id.buy_textview);
        TextView sellLabel = findViewById(R.id.sell_textview);
        TextView walletLabel = findViewById(R.id.wallet_textview);

        buyLabel.setText("Buy");
        sellLabel.setText("Sell");
        walletLabel.setText("Wallet: $" + String.format("%.2f", currentPlayer.getWallet()));
    }

    @Override
    public void onResume() {
        super.onResume();
        buyAdapter.setGoods(buyViewModel.getBuyTradeGoods());
        sellAdapter.setGoods(sellViewModel.getSellTradeGoods());
    }

}
