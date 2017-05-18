package com.example.michihiroyamasaki.sample.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderModelTest {
    private OrderModel orderModel;
    @Before
    public void prepareOrder(){
        orderModel = new OrderModel();
        orderModel.setOrderId(1);

        UserModel u = new UserModel();
        orderModel.setUser(u);

        GoodsModel goods1 = new GoodsModel("商品1", BigInteger.valueOf(100));
        goods1.setGoodsId(1);

        GoodsModel goods2 = new GoodsModel("商品2", BigInteger.valueOf(200));
        goods2.setGoodsId(2);

        orderModel.addAmount(goods1, 3);
        orderModel.addAmount(goods2, 2);
    }

    @Test
    public void properlyCalculatesCount(){
        assertThat(orderModel.getCount()).isEqualTo(5);
    }

    @Test
    public void properlyCaluculateSum(){
        assertThat(orderModel.getSum()).isEqualTo(BigInteger.valueOf(700));
    }
}