package com.example.michihiroyamasaki.sample.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michihiroyamasaki.sample.R;
import com.example.michihiroyamasaki.sample.dao.UserDaoImpl;
import com.example.michihiroyamasaki.sample.model.UserModel;
import com.example.michihiroyamasaki.sample.presenter.LoginPresenter;
import com.example.michihiroyamasaki.sample.presenter.LoginView;
import com.example.michihiroyamasaki.sample.presenter.UserDao;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });


        TextView link = (TextView)findViewById(R.id.goRegistText);

        SpannableString string = new SpannableString(getResources().getString(R.string.btn_toregist));
        string.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                goRegist();
            }
        },0,string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        link.setText(string);
        link.setMovementMethod(LinkMovementMethod.getInstance());


        try{
            UserDao userDao = new UserDaoImpl(this);
            loginPresenter = new LoginPresenter(this, userDao);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void goRegist() {
        Intent i = new Intent(this, RegistActivity.class);
        startActivity(i);
    }

    private void doLogin() {
        TextView userIdText = (TextView) findViewById(R.id.userIdText);
        TextView passwordText = (TextView) findViewById(R.id.passwordText);

        String userId = userIdText.getText().toString();
        String password = passwordText.getText().toString();

        loginPresenter.doLogin(userId, password);
    }

    @Override
    public void goOrder(final UserModel loggedIn){
        Intent i = new Intent(this, OrderActivity.class);
        i.putExtra("loggedIn", loggedIn);
        startActivity(i);
    }

    @Override
    public void showFailureMessage(){
        new AlertDialog.Builder(this)
                .setMessage(R.string.login_failure)
                .setPositiveButton(R.string.btn_accept, null)
                .show();
    }
}
