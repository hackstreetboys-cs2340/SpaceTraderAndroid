package edu.gatech.cs2340.spacetrader.views;

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

public class MarketActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        RecyclerView buyRecyclerView = findViewById(R.id.buy_market_list);
        RecyclerView sellRecyclerView = findViewById(R.id.sell_market_list);
        BuyMarketItemAdapter buyAdapter = new BuyMarketItemAdapter();
        buyRecyclerView.setAdapter(buyAdapter);
        sellRecyclerView.setAdapter(buyAdapter);
        buyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sellRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView buyLabel = findViewById(R.id.buy_textview);
        TextView sellLabel = findViewById(R.id.sell_textview);
        TextView walletLabel = findViewById(R.id.wallet_textview);

        buyLabel.setText("Buy");
        sellLabel.setText("Sell");
        walletLabel.setText("Wallet: 0");

    }
}
