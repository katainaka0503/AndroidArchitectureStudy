package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import java.net.IDN;
import java.util.Date;
import java.util.List;

/**
 * RegistPresenterが使うViewのインタフェース
 */
public interface RegistView {
    /**
     * ログインIDを表示する
     * @param loginId ログインID
     */
    void showLoginId(String loginId);

    /**
     * パスワードを表示する
     * @param password パスワード
     */
    void showPassword(String password);

    /**
     * 性別を女性として表示する
     */
    void showAsFemale();

    /**
     * 性別を男性として表示する
     */
    void showAsMale();

    /**
     * 都道府県情報を表示する
     * @param prefecture 都道府県名の文字列
     */
    void showPrefecture(String prefecture);

    /**
     * 誕生日を表示する
     * @param birthDay 誕生日
     */
     void showBirthday(Date birthDay);

    /**
     * エラーメッセージを表示する
     * @param messageIds エラーメッセージのリスト
     */
    void showErrorMessage(List<Integer> messageIds);

    /**
     * 確認画面に遷移する
     * @param user 入力されたユーザ情報
     */
    void goConfirmation(UserModel user);
}
