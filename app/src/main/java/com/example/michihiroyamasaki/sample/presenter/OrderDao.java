package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.OrderModel;

import java.sql.SQLException;

public interface OrderDao {
    void create(OrderModel newOrder) throws SQLException;
}
