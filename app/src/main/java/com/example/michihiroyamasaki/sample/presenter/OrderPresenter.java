package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.GoodsModel;
import com.example.michihiroyamasaki.sample.model.OrderDetailModel;
import com.example.michihiroyamasaki.sample.model.OrderModel;
import com.example.michihiroyamasaki.sample.model.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 注文画面のPresenter
 */
public class OrderPresenter {
    private OrderView view;
    private OrderDao orderDao;
    private UserModel loggedIn;
    private OrderModel order = new OrderModel();
    private List<GoodsModel> goodsList = new ArrayList<>();

    public OrderPresenter(OrderView view, OrderDao orderDao, GoodsDao goodsDao, UserModel loggedIn) {
        this.view = view;
        this.orderDao = orderDao;
        this.loggedIn=loggedIn;
        order.setUser(loggedIn);

        try {
            this.goodsList = goodsDao.findAll();
        } catch (SQLException e){
            e.printStackTrace();
        }

        view.showLoginId(loggedIn.getUserId());
        showGoodsList();

    }

    private void showGoodsList(){
        List<GoodsDto> list = new ArrayList<>();
        for (GoodsModel goods : goodsList) {
            GoodsDto goodsDto = new GoodsDto(goods.getGoodsId(), goods.getGoodsName(), goods.getPrice());
            list.add(goodsDto);
        }
        view.showGoodsList(list);
    }

    public void addGoods(int position){
        GoodsModel goods = goodsList.get(position);
        order.addAmount(goods, 1);
    }



    public void doOrder(){
        try{
            orderDao.create(order);
            order = new OrderModel();

            order.setUser(loggedIn);
            view.dismissPopup();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void showCart(){
        if(order.getOrderDetails().isEmpty()){
            view.showCartEmpty();
        } else {
            view.showCartHasContent(getCartDataAsEnry(), order.getCount(), order.getSum().intValue());
        }
    }

    /**
     * ポップアップを消す
     */
    public void backFromWindow(){
        view.dismissPopup();
    }

    private List<OrderDetailDto> getCartDataAsEnry(){
        List<OrderDetailDto> toShow = new ArrayList<>();
        for (OrderDetailModel od : order.getOrderDetails()) {
            GoodsModel goods = od.getGoods();
            OrderDetailDto orderDetailDto =
                    new OrderDetailDto(
                            goods.getGoodsId(),
                            goods.getGoodsName(),
                            goods.getPrice(),
                            od.getCount());
            toShow.add(orderDetailDto);
        }
        return toShow;
    }
}
