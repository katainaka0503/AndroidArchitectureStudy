package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import java.sql.SQLException;

/**
 * ログイン画面のPresenter
 */
public class LoginPresenter {
    private LoginView view;
    private UserDao userDao;

    public LoginPresenter(LoginView view, UserDao userDao){
        this.view = view;
        this.userDao = userDao;
    }

    public void doLogin(String userId, String password){

        try {
            UserModel found = userDao.findByUserIdAndPassword(userId, password);
            if(found == null){
                view.showFailureMessage();
            } else {
                view.goOrder(found);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
