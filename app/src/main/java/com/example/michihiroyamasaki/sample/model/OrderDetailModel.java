package com.example.michihiroyamasaki.sample.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 注文明細を表すモデルです。
 *
 * 注文明細は注文明細ID、当該明細が含まれる注文、商品、個数の情報を持ちます。
 */
@DatabaseTable
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "order")
public class OrderDetailModel {
    @DatabaseField(generatedId = true)
    private long orderDetailId;

    @DatabaseField(foreign = true)
    private OrderModel order;

    @DatabaseField(foreign = true, foreignAutoRefresh =  true)
    private GoodsModel goods;
    @DatabaseField
    private int count;

    /**
     * この明細の金額を計算します
     *
     * @return 金額
     */
    BigInteger getAmount(){
        return this.getGoods().getPrice().multiply(BigInteger.valueOf(this.getCount()));
    }

    OrderDetailModel(OrderModel order, GoodsModel goods, int count) {
        this.order = order;
        this.goods = goods;
        this.count = count;
    }

}
