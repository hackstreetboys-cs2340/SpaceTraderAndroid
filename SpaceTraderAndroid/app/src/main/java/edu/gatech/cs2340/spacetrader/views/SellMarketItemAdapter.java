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

public class SellMarketItemAdapter extends RecyclerView.Adapter<SellMarketItemAdapter.SellMarketItemViewHolder> {

    private List<TradeGood> goods = new ArrayList<>();
    private OnSellGoodClickListener listener;

    @NonNull
    @Override
    public SellMarketItemAdapter.SellMarketItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.market_sell_item, viewGroup, false);

        return new SellMarketItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SellMarketItemAdapter.SellMarketItemViewHolder sellMarketItemViewHolder, int i) {
        TradeGood good = goods.get(i);

        sellMarketItemViewHolder.itemName.setText(good.getName());
        sellMarketItemViewHolder.itemPrice.setText(good.getQuantity() + " x " + String.format("%.2f", good.getFinalPrice()));
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public void setGoods(List<TradeGood> goods) {
        this.goods = goods;
        notifyDataSetChanged();
    }

    class SellMarketItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView itemPrice;

        public SellMarketItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_good_name);
            itemPrice = itemView.findViewById(R.id.text_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onSellGoodClicked(goods.get(position));
                    }
                }
            });
        }
    }
    public interface OnSellGoodClickListener {
        void onSellGoodClicked(TradeGood good);
    }

    public void setOnSellGoodClickListener(OnSellGoodClickListener listener) {
        this.listener = listener;
    }
}
