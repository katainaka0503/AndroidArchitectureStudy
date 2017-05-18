package com.example.michihiroyamasaki.sample.presenter;

import java.math.BigInteger;

import lombok.Value;

/**
 * 商品情報を画面に表示するためのDTO
 */
@Value
public class GoodsDto {
    private long goodsId;
    private String goodsName;
    private BigInteger price;
}
