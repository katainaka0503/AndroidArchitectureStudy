package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 確認画面のPresenter
 */
public class ConfirmationPresenter {
    private UserModel user;
    private ConfirmationView view;
    private UserDao dao;

    public ConfirmationPresenter(UserModel user, ConfirmationView view, UserDao dao) {
        this.user = user;
        this.view = view;
        this.dao = dao;

        showUserInfo();
    }

    /**
     * 入力された情報を確認画面に表示する
     */
    private void showUserInfo(){
        view.showLoginId(user.getUserId());
        if(UserModel.Gender.Male == user.getGender()){
            view.showAsMale();
        } else {
            view.showAsFemale();
        }
        view.showPrefecture(user.getPrefecture());

        view.showBirthday(user.getBirthDay());
    }

    /**
     * 登録を行う
     */
    public void doRegist() {
        try {
            dao.create(user);
            view.goBackToLogin();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * 登録画面に戻る
     */
    public void goBack(){
        view.goBackToRegist(user);
    }
}
