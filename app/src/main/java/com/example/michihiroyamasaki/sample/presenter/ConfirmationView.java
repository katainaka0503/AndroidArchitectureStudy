package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import java.util.Date;

/**
 * ConfirmationPresenterが扱うViewを表すインタフェース
 */
public interface ConfirmationView {
    /**
     * 登録画面に戻る
     * @param u 保持しているユーザの情報
     */
    void goBackToRegist(UserModel u);

    /**
     * ログイン画面に戻る
     */
    void goBackToLogin();

    /**
     * ログインIDを画面に表示する
     * @param loginId ログインID
     */
    void showLoginId(String loginId);

    /**
     * 性別を女性として表示する
     */
    void showAsFemale();

    /**
     * 性別を男性として表示する
     */
    void showAsMale();

    /**
     * 都道府県の情報を表示する
     * @param prefecture 都道府県名
     */
    void showPrefecture(String prefecture);

    /**
     * 誕生日の情報を表示する
     * @param birthday 誕生日の情報
     */
    void showBirthday(Date birthday);
}
