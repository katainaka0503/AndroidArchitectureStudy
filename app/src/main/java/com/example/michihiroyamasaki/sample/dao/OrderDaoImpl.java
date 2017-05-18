package com.example.michihiroyamasaki.sample.dao;

import android.content.Context;

import com.example.michihiroyamasaki.sample.model.OrderDetailModel;
import com.example.michihiroyamasaki.sample.model.OrderModel;
import com.example.michihiroyamasaki.sample.presenter.OrderDao;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import static com.j256.ormlite.android.apptools.OpenHelperManager.getHelper;


public class OrderDaoImpl implements OrderDao {

    private Dao<OrderModel, Long> orderDaoRaw;
    private Dao<OrderDetailModel, Long> orderDetailDaoRaw;
    public OrderDaoImpl(Context context) throws SQLException {
        AppDatabaseHelper helper = getHelper(context, AppDatabaseHelper.class);
        orderDaoRaw = helper.getDao(OrderModel.class);
        orderDetailDaoRaw = helper.getDao(OrderDetailModel.class);
    }

    @Override
    public void create(OrderModel newOrder) throws SQLException {
        orderDaoRaw.create(newOrder);
        for (OrderDetailModel newOrderDetail : newOrder.getOrderDetails()) {
            orderDetailDaoRaw.create(newOrderDetail);
        }
    }
}
