package edu.gatech.cs2340.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;

public class BuyMarketItemAdapter extends RecyclerView.Adapter<BuyMarketItemAdapter.BuyMarketItemViewHolder> {

    @NonNull
    @Override
    public BuyMarketItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.market_item, viewGroup, false);
        return new BuyMarketItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyMarketItemViewHolder buyMarketItemViewHolder, int i) {
        buyMarketItemViewHolder.itemName.setText("placeholder");
        buyMarketItemViewHolder.itemPrice.setText("0");
        buyMarketItemViewHolder.buyButton.setText("Buy");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class BuyMarketItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView itemPrice;
        private Button buyButton;

        public BuyMarketItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_good_name);
            itemPrice = itemView.findViewById(R.id.text_price);
            buyButton = itemView.findViewById(R.id.market_button);
        }
    }
}