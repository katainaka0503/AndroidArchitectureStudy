package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Mock
    private UserDao userDao;

    @Mock
    private LoginView loginView;

    @InjectMocks
    private LoginPresenter loginPresenter;

    private UserModel user;
    private String userId;
    private String password;

    @Before
    public void prepareUser() {
        userId = "userId";
        password = "taroPassword";


        user = new UserModel();
        user.setUserId(userId);
        user.setPassword(password);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void loginSuccessIfMatchesUserIdAndPassword() throws Exception {
        when(userDao.findByUserIdAndPassword(userId, password)).thenReturn(user);

        loginPresenter.doLogin(userId, password);

        verify(loginView, only()).goOrder(user);
    }

    @Test
    public void loginFailureIfPasswordDoesNotMatch() throws SQLException {
        when(userDao.findByUserIdAndPassword(userId, password)).thenReturn(null);

        loginPresenter.doLogin(userId, password);

        verify(loginView, only()).showFailureMessage();
    }

}