package com.example.michihiroyamasaki.sample.presenter;

import java.util.List;

/**
 * OrderPresenterが扱うViewのインタフェース
 */
public interface OrderView {
    /**
     * ポップアップを消す
     */
    void dismissPopup();

    /**
     * 商品リストを表示する
     *
     * @param data 商品情報
     */
    void showGoodsList(List<GoodsDto> data);

    /**
     *　空のカートの内容を表示する
     */
    void showCartEmpty();

    /**
     * カートの内容を表示する
     *
     * @param list カートの内容
     * @param count 合計点数
     * @param sum 合計金額
     */
    void showCartHasContent(List<OrderDetailDto> list, int count, int sum);

    /**
     * ログインIDを表示する
     * @param loginId ログインID
     */
    void showLoginId(String loginId);
}
