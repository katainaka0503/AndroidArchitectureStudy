package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

/**
 * LoginPresenterが使用するViewのインタフェース
 */
public interface LoginView {
    /**
     * 注文画面に遷移
     * @param user ログインしているユーザ
     */
    void goOrder(UserModel user);

    /**
     * ログインに失敗したメッセージを出す
     */
    void showFailureMessage();
}
