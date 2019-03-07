package edu.gatech.cs2340.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

public class BuyMarketItemAdapter extends RecyclerView.Adapter<BuyMarketItemAdapter.BuyMarketItemViewHolder> {

    private List<TradeGood> goods = new ArrayList<>();

    @NonNull
    @Override
    public BuyMarketItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.market_item, viewGroup, false);

        return new BuyMarketItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyMarketItemViewHolder buyMarketItemViewHolder, int i) {
        TradeGood good = goods.get(i);

        buyMarketItemViewHolder.itemName.setText(good.getName());
        if (good.getFinalPrice() <= 0) {
            buyMarketItemViewHolder.itemPrice.setText("N/A");
        } else {
            buyMarketItemViewHolder.itemPrice.setText(String.format("%.2f", good.getFinalPrice()));
        }
        buyMarketItemViewHolder.buyButton.setText("Buy");
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public void setGoods(List<TradeGood> goods) {
        this.goods = goods;
        notifyDataSetChanged();
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