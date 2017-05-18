package com.example.michihiroyamasaki.sample.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 注文を表すモデルです。
 *
 * 注文は注文IDおよび注文したユーザ、注文明細の一覧の情報を持ちます
 */
@DatabaseTable
@Data
public class OrderModel {
    @DatabaseField(generatedId = true)
    private long orderId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private UserModel user;

    private List<OrderDetailModel> orderDetails = new ArrayList<>();

    /**
     * 合計金額を計算します
     *
     * @return 合計金額
     */
    public BigInteger getSum(){
        BigInteger sum = BigInteger.ZERO;

        for(OrderDetailModel od : getOrderDetails()){
            sum = sum.add(od.getAmount());
        }

        return sum;
    }

    /**
     * 合計点数を計算します
     * @return 合計点数
     */
    public int getCount(){
        int sum = 0;

        for(OrderDetailModel od: getOrderDetails()){
            sum += od.getCount();
        }
        return sum;
    }

    /**
     * 注文明細に商品を一つ追加します。
     *
     * 同じ商品の注文明細が既に存在する場合は、当該の注文明細が保持する指定された個数分増やします。
     * 同じ商品の注文明細が存在しない場合は、当該商品の明細を作成し個数を指定された個数として追加します。
     *
     * @param goods 追加する商品
     * @param count 個数
     */
    public void addAmount(GoodsModel goods, int count) {
        boolean found = false;
        for(OrderDetailModel od: getOrderDetails()){
            if(od.getGoods().equals(goods)){
                od.setCount(od.getCount() + count);
                found = true;
            }
        }

        if(!found){
            OrderDetailModel newDetail = new OrderDetailModel(this, goods, count);
            getOrderDetails().add(newDetail);
        }
    }
}
