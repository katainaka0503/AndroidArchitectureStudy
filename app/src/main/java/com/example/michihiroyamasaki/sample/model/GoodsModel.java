package com.example.michihiroyamasaki.sample.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 商品を表すモデルです。
 *
 * 商品は商品ID、商品名、価格の情報をもちます
 */
@DatabaseTable
@Data
@NoArgsConstructor
public class GoodsModel {
    @DatabaseField(generatedId = true)
    private long goodsId;
    @DatabaseField
    private String goodsName;

    public GoodsModel(String goodsName, BigInteger price) {
        this.goodsName = goodsName;
        this.price = price;
    }

    @DatabaseField
    private BigInteger price;
}
