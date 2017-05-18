package com.example.michihiroyamasaki.sample.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michihiroyamasaki.sample.R;
import com.example.michihiroyamasaki.sample.presenter.GoodsDto;
import com.example.michihiroyamasaki.sample.utils.FormatUtils;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
class GoodsRecyclerAdapter extends RecyclerView.Adapter<GoodsRecyclerAdapter.ViewHolder> {

    private final List<GoodsDto> goodsList;

    @Setter
    private View.OnClickListener onItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_goods, parent, false);

        view.setOnClickListener(onItemClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GoodsDto toShow = goodsList.get(position);

        holder.setGoodsNameText(toShow.getGoodsName());
        holder.setGoodsPriceText(FormatUtils.getInstance().currencyFormat(toShow.getPrice()));
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView goodsNameText;
        private final TextView goodsPriceText;

        void setGoodsNameText(String goodsName){
            this.goodsNameText.setText(goodsName);
        }

        void setGoodsPriceText(String goodsPriceText){
            this.goodsPriceText.setText(goodsPriceText);
        }

        ViewHolder(View v){
            super(v);
            goodsNameText = (TextView)v.findViewById(R.id.goods_name);
            goodsPriceText = (TextView)v.findViewById(R.id.goods_price);
        }
    }
}
