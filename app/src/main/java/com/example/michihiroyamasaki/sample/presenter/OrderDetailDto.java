package com.example.michihiroyamasaki.sample.presenter;

import java.math.BigInteger;

import lombok.Value;

/**
 * 注文明細の情報を画面に表示するためのDTO
 */
@Value
public class OrderDetailDto {
    private long goodsId;
    private String goodsName;
    private BigInteger goodsPrice;
    private int count;
}
