package com.example.michihiroyamasaki.sample.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michihiroyamasaki.sample.R;
import com.example.michihiroyamasaki.sample.presenter.OrderDetailDto;
import com.example.michihiroyamasaki.sample.utils.FormatUtils;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class OrderDetailRecyclerAdapter extends RecyclerView.Adapter<OrderDetailRecyclerAdapter.ViewHolder> {
    private final List<OrderDetailDto> orderDetailEntries;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView goodsNameText;
        private final TextView goodsPriceText;
        private final TextView goodsCountText;
        ViewHolder(View v){
            super(v);

            goodsNameText = (TextView)v.findViewById(R.id.goods_name);
            goodsPriceText = (TextView)v.findViewById(R.id.goods_price);
            goodsCountText = (TextView)v.findViewById(R.id.goods_count);
        }

        void setGoodsNameText(String goodsName){
            goodsNameText.setText(goodsName);
        }

        void setGoodsPriceText(String goodsPrice){
            goodsPriceText.setText(goodsPrice);
        }

        void setGoodsCountText(String goodsCount){
            goodsCountText.setText(goodsCount);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FormatUtils formatUtils = FormatUtils.getInstance();
        OrderDetailDto toShow = orderDetailEntries.get(position);

        holder.setGoodsNameText(toShow.getGoodsName());
        holder.setGoodsPriceText(formatUtils.currencyFormat(toShow.getGoodsPrice()));
        holder.setGoodsCountText(formatUtils.countFormat(toShow.getCount()));
    }

    @Override
    public int getItemCount() {
        return orderDetailEntries.size();
    }
}
