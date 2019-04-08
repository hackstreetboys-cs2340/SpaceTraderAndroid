package edu.gatech.cs2340.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.tradegoods.TradeGood;

/**
 * Buy Market Item Adapter
 */
public class BuyMarketItemAdapter extends RecyclerView.Adapter<BuyMarketItemAdapter.BuyMarketItemViewHolder> {

    private List<TradeGood> goods = new ArrayList<>();
    private OnBuyGoodClickListener listener;

    @NonNull
    @Override
    public BuyMarketItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.market_buy_item, viewGroup, false);

        return new BuyMarketItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyMarketItemViewHolder buyMarketItemViewHolder, int i) {
        TradeGood good = goods.get(i);

        Log.d("APP", "Binding: " + i + " " + goods.get(i));

        buyMarketItemViewHolder.itemName.setText(good.getName());
        if (good.getFinalPrice() <= 0) {
            buyMarketItemViewHolder.itemPrice.setText("N/A");
        } else {
            buyMarketItemViewHolder.itemPrice.setText(String.format("%.2f", good.getFinalPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    /**
     * set the goods of the class
     * @param goods list of goods
     */
    public void setGoods(List<TradeGood> goods) {
        this.goods = goods;
        notifyDataSetChanged();
    }

    class BuyMarketItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView itemPrice;

        public BuyMarketItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_good_name);
            itemPrice = itemView.findViewById(R.id.text_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onBuyGoodClicked(goods.get(position));
                    }
                }
            });
        }
    }


    public interface OnBuyGoodClickListener {
        /**
         * buy good click listener
         * @param good good clicked
         */
        void onBuyGoodClicked(TradeGood good);
    }

    /**
     * set the click listener
     * @param listener click listener
     */
    public void setOnBuyGoodClickListener(OnBuyGoodClickListener listener) {
        this.listener = listener;
    }
}