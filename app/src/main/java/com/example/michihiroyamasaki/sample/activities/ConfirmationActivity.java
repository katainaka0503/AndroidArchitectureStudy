package com.example.michihiroyamasaki.sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michihiroyamasaki.sample.R;
import com.example.michihiroyamasaki.sample.dao.UserDaoImpl;
import com.example.michihiroyamasaki.sample.model.UserModel;
import com.example.michihiroyamasaki.sample.presenter.ConfirmationPresenter;
import com.example.michihiroyamasaki.sample.presenter.ConfirmationView;
import com.example.michihiroyamasaki.sample.presenter.UserDao;
import com.example.michihiroyamasaki.sample.utils.FormatUtils;

import java.sql.SQLException;
import java.util.Date;

public class ConfirmationActivity extends AppCompatActivity implements ConfirmationView {
    private ConfirmationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        UserModel user = getIntent().getParcelableExtra("user");
        try {
            UserDao userDao = new UserDaoImpl(this);
            presenter = new ConfirmationPresenter(user, this, userDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Button registButton = (Button)findViewById(R.id.registButton);
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doRegist();
            }
        });

        Button goBackButton = (Button)findViewById(R.id.goBackToRegistButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goBack();
            }
        });
    }

    @Override
    public void showLoginId(String loginId) {
        TextView loginIdText = (TextView) findViewById(R.id.loginIdValueText);
        loginIdText.setText(loginId);
    }

    @Override
    public void showAsFemale() {
        TextView genderText = (TextView) findViewById(R.id.genderValueText);
        genderText.setText(R.string.female);
    }

    @Override
    public void showAsMale() {
        TextView genderText = (TextView) findViewById(R.id.genderValueText);
        genderText.setText(R.string.male);
    }

    @Override
    public void showPrefecture(String prefecture) {
        TextView prefectureText = (TextView) findViewById(R.id.prefectureValueText);
        prefectureText.setText(prefecture);
    }

    @Override
    public void showBirthday(Date birthday) {
        String birthdayAsString = FormatUtils.getInstance().dateFormat(birthday);
        TextView birthDayText = (TextView) findViewById(R.id.birthDayValueText);
        birthDayText.setText(birthdayAsString);
    }

    @Override
    public void goBackToRegist(UserModel userModel){
        Intent i = new Intent(this , RegistActivity.class);
        i.putExtra("user", userModel);
        startActivity(i);
        finish();
    }

    @Override
    public void goBackToLogin(){
        finish();
    }
}
