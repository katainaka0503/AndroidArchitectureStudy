package com.example.michihiroyamasaki.sample.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.michihiroyamasaki.sample.R;
import com.example.michihiroyamasaki.sample.dao.GoodsDaoImpl;
import com.example.michihiroyamasaki.sample.dao.OrderDaoImpl;
import com.example.michihiroyamasaki.sample.model.UserModel;
import com.example.michihiroyamasaki.sample.presenter.GoodsDao;
import com.example.michihiroyamasaki.sample.presenter.GoodsDto;
import com.example.michihiroyamasaki.sample.presenter.OrderDao;
import com.example.michihiroyamasaki.sample.presenter.OrderDetailDto;
import com.example.michihiroyamasaki.sample.presenter.OrderPresenter;
import com.example.michihiroyamasaki.sample.presenter.OrderView;
import com.example.michihiroyamasaki.sample.utils.FormatUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderView {


    private OrderPresenter orderPresenter;
    private PopupWindow cartPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order);

        UserModel loggedIn = getIntent().getParcelableExtra("loggedIn");
        try {
            GoodsDao goodsDao = new GoodsDaoImpl(this);
            OrderDao orderDao = new OrderDaoImpl(this);
            orderPresenter = new OrderPresenter(this, orderDao, goodsDao, loggedIn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RecyclerView goodsRecyclerView = (RecyclerView) findViewById(R.id.goodsRecyclerView);
        goodsRecyclerView.setHasFixedSize(true);
        goodsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Button cartButton = (Button)findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderPresenter.showCart();
            }
        });
    }

    @Override
    public void showLoginId(String loginId){
        //ToDO
    }

    @Override
    public void showGoodsList(List<GoodsDto> list) {
        final RecyclerView goodsRecyclerView = (RecyclerView)findViewById(R.id.goodsRecyclerView);

        GoodsRecyclerAdapter sa = new GoodsRecyclerAdapter(list);

        sa.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = goodsRecyclerView.getChildAdapterPosition(v);
                orderPresenter.addGoods(position);
            }
        });

        goodsRecyclerView.setAdapter(sa);
    }

    @Override
    public void showCartEmpty(){
        View cartView = getPopupView(0, 0);

        setViewAsNoGoods(cartView);

        createPopup(cartView);

        showPopup();
    }

    private void setViewAsNoGoods(View cartView) {
        final Button order = (Button) cartView.findViewById(R.id.order);
        order.setClickable(false);

        TextView message = (TextView) cartView.findViewById(R.id.message);
        message.setText(R.string.no_goods);
    }

    @Override
    public void showCartHasContent(List<OrderDetailDto> list, int count, int sum) {
        View cartView = getPopupView(count, sum);

        setDataToCartView(cartView, list);

        createPopup(cartView);

        registOrderAction(cartView);

        showPopup();
    }

    private void showPopup() {
        cartPopup.showAtLocation(findViewById(R.id.cartButton), Gravity.CENTER, 0, 0);
    }

    private void registOrderAction(View cartView) {
        final Button order = (Button) cartView.findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderPresenter.doOrder();
            }
        });
    }

    private void createPopup(View cartView) {
        cartPopup = new PopupWindow(cartView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        cartPopup.setFocusable(true);


        Button back = (Button) cartView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderPresenter.backFromWindow();
            }
        });

        cartPopup.setBackgroundDrawable(getDrawable(R.drawable.background_popup));
    }

    private void setDataToCartView(View cartView, List<OrderDetailDto> list) {
        OrderDetailRecyclerAdapter orderDetailListAdapter = new OrderDetailRecyclerAdapter(list);

        RecyclerView cartList = (RecyclerView)cartView.findViewById(R.id.cart_list);
        cartList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartList.setLayoutManager(layoutManager);
        cartList.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        cartList.setAdapter(orderDetailListAdapter);
    }

    @NonNull
    private View getPopupView(int count, int sum) {
        FormatUtils formatUtils = FormatUtils.getInstance();
        View cartView = View.inflate(this, R.layout.popup_cart, null);

        TextView countText = (TextView)cartView.findViewById(R.id.count);
        countText.setText(formatUtils.countFormat(count));
        TextView sumText = (TextView)cartView.findViewById(R.id.sum);
        sumText.setText(formatUtils.currencyFormat(sum));
        return cartView;
    }

    @Override
    public void dismissPopup(){
        cartPopup.dismiss();
    }
}
