package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.GoodsModel;
import com.example.michihiroyamasaki.sample.model.OrderModel;
import com.example.michihiroyamasaki.sample.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class OrderPresenterTest {
    @Mock
    private OrderView view;
    @Mock
    private OrderDao orderDao;
    @Mock
    private GoodsDao goodsDao;

    private UserModel loggedIn = new UserModel() {
        {
            setUserId("userId");
            setPassword("password");
            setGender(Gender.Female);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, 2015);
            c.set(Calendar.MONTH, Calendar.APRIL);
            c.set(Calendar.DAY_OF_MONTH, 15);

            setBirthDay(c.getTime());
            setPrefecture("北海道");
        }
    };

    private List<GoodsModel> goodsList = new ArrayList<GoodsModel>() {
        {
            GoodsModel goods1 = new GoodsModel();
            goods1.setGoodsId(1);
            goods1.setGoodsName("goods1");
            goods1.setPrice(BigInteger.valueOf(2000));
            add(goods1);

            GoodsModel goods2 = new GoodsModel();
            goods2.setGoodsId(2);
            goods2.setGoodsName("goods2");
            goods2.setPrice(BigInteger.valueOf(3000));
            add(goods2);

            System.out.println("");
        }
    };

    private OrderPresenter orderPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(goodsDao.findAll()).thenReturn(goodsList);

        this.loggedIn = new UserModel();

        orderPresenter = new OrderPresenter(view, orderDao, goodsDao, loggedIn);
    }

    @Test
    public void showGoodsCallsViewsShowGoods() {

        List<GoodsDto> list = new ArrayList<>();

        for (GoodsModel goods : goodsList) {
            GoodsDto goodsDto = new GoodsDto(goods.getGoodsId(), goods.getGoodsName(), goods.getPrice());
            list.add(goodsDto);
        }

        verify(view, times(1)).showGoodsList(list);
    }

    @Test
    public void backFromWindowCallsDismissCallback() {
        orderPresenter.backFromWindow();

        verify(view, times(1)).dismissPopup();
    }

    @Test
    public void showCartIfCartIsEmptyCallsShowCartEmpty() {
        orderPresenter.showCart();

        verify(view, times(1)).showCartEmpty();
    }

    @Test
    public void showCartIfHasContentCallsShowCartHasContent() {
        int countOfGoods0 = 3;
        int countOfGoods1 = 2;
        for (int i = 0; i < countOfGoods0; i++) {
            orderPresenter.addGoods(0);
        }
        for (int i = 0; i < countOfGoods1; i++) {
            orderPresenter.addGoods(1);
        }

        orderPresenter.showCart();


        GoodsModel goods0 = goodsList.get(0);
        GoodsModel goods1 = goodsList.get(1);

        List<OrderDetailDto> list = Arrays.asList(
                new OrderDetailDto(
                        goods0.getGoodsId(),
                        goods0.getGoodsName(),
                        goods0.getPrice(),
                        countOfGoods0),

                new OrderDetailDto(
                        goods1.getGoodsId(),
                        goods1.getGoodsName(),
                        goods1.getPrice(),
                        countOfGoods1
                ));

        int sumToBe = goods0.getPrice().intValue() * countOfGoods0 + goods1.getPrice().intValue() * countOfGoods1;
        verify(view, times(1)).showCartHasContent(list, 5, sumToBe);

    }

    @Test
    public void doOrderPersistsData() throws SQLException {
        for (int i = 0; i < 3; i++) {
            orderPresenter.addGoods(0);
        }
        for (int i = 0; i < 2; i++) {
            orderPresenter.addGoods(1);
        }

        orderPresenter.doOrder();

        OrderModel order = new OrderModel();

        order.setUser(loggedIn);

        order.addAmount(goodsList.get(0), 3);
        order.addAmount(goodsList.get(1), 2);

        verify(orderDao, times(1)).create(order);

    }
}