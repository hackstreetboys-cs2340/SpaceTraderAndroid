package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;
import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.PlayerInteractor;
import edu.gatech.cs2340.spacetrader.viewmodels.BuyGoodListingViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.SellGoodListingViewModel;

/**
 * Market Activity Class
 */
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
        TextView capacityLabel = findViewById(R.id.capacity_textview);
        Button returnToPlanet = findViewById(R.id.return_to_planet);

        buyLabel.setText("Buy");
        sellLabel.setText("Sell");
        walletLabel.setText("Wallet: $" + String.format("%.2f", currentPlayer.getWallet()));
        capacityLabel.setText("Capacity: " + currentPlayer.getSize() + " / " + currentPlayer.getCapacity());
    }

    /**
     * returns player back to the planet screen
     * @param view view
     */
    public void onReturnPressed(View view) {
        Intent intent = new Intent(MarketActivity.this, PlanetActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        buyAdapter.setGoods(buyViewModel.getBuyTradeGoods());
        sellAdapter.setGoods(sellViewModel.getSellTradeGoods());

        buyAdapter.setOnBuyGoodClickListener(new BuyMarketItemAdapter.OnBuyGoodClickListener() {
            @Override
            public void onBuyGoodClicked(TradeGood good) {
                sellViewModel.addPlayerGood(good);
                sellAdapter.setGoods(sellViewModel.getSellTradeGoods());
                TextView walletLabel = findViewById(R.id.wallet_textview);
                walletLabel.setText("Wallet: $" + String.format("%.2f", currentPlayer.getWallet()));
                TextView capacityLabel = findViewById(R.id.capacity_textview);
                capacityLabel.setText("Capacity: " + currentPlayer.getSize() + " / " + currentPlayer.getCapacity());
            }
        });

        sellAdapter.setOnSellGoodClickListener(new SellMarketItemAdapter.OnSellGoodClickListener() {
            @Override
            public void onSellGoodClicked(TradeGood good) {
                buyViewModel.removePlayerGood(good);
                sellAdapter.setGoods(sellViewModel.getSellTradeGoods());
                TextView walletLabel = findViewById(R.id.wallet_textview);
                walletLabel.setText("Wallet: $" + String.format("%.2f", currentPlayer.getWallet()));
                TextView capacityLabel = findViewById(R.id.capacity_textview);
                capacityLabel.setText("Capacity: " + currentPlayer.getSize() + " / " + currentPlayer.getCapacity());
            }
        });
    }
}
