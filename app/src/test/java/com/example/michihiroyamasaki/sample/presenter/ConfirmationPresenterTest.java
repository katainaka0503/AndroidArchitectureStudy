package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ConfirmationPresenterTest {
    @Mock
    private ConfirmationView view;

    @Mock
    private UserDao userDao;

    private ConfirmationPresenter confirmationPresenter;
    private UserModel user;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        user = new UserModel();
        user.setUserId("userId");
        user.setPassword("password");
        user.setGender(UserModel.Gender.Female);
        user.setPrefecture("北海道");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2015);
        c.set(Calendar.MONTH, Calendar.APRIL);
        c.set(Calendar.DAY_OF_MONTH, 15);

        user.setBirthDay(c.getTime());

        confirmationPresenter = new ConfirmationPresenter(user, view , userDao);
    }

    @Test
    public void whendoRegistThenregisterProperly() throws Exception{
        confirmationPresenter.doRegist();

        verify(userDao, times(1)).create(user);
        verify(view, times(1)).goBackToLogin();
    }

    @Test
    public void whenGoBackToRegistPassesUser(){
        confirmationPresenter.goBack();

        verify(view, times(1)).goBackToRegist(user);
    }
}