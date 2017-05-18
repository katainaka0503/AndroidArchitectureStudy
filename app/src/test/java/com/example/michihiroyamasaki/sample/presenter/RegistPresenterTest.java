package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RegistPresenterTest {

    @Mock
    private RegistView view;

    private RegistPresenter registPresenter;

    @Mock
    private UserModel user;

    @Before
    public void prepareUser(){
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

        user = spy(user);

        registPresenter = new RegistPresenter(view, user);
    }

    @Test
    public void whenUserValidateFailShowErrorMessage(){
        int anError = 1;
        List<Integer> errors = Collections.singletonList(anError);
        doReturn(errors).when(user).validate();

        registPresenter.validate();

        verify(view, times(1)).showErrorMessage(errors);
    }

    @Test
    public void whenUserValidateSucceedGoConfirm(){
        List<Integer> noErrors = new ArrayList<>();
        doReturn(noErrors).when(user).validate();

        registPresenter.validate();

        verify(view, times(1)).goConfirmation(user);
    }
}