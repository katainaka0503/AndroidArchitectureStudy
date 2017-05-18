package com.example.michihiroyamasaki.sample.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.example.michihiroyamasaki.sample.R;
import com.example.michihiroyamasaki.sample.model.UserModel;
import com.example.michihiroyamasaki.sample.presenter.RegistPresenter;
import com.example.michihiroyamasaki.sample.presenter.RegistView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistActivity extends AppCompatActivity implements RegistView{

    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regist);

        UserModel user = getIntent().getParcelableExtra("user");
        if(user != null){
            registPresenter = new RegistPresenter(this, user);
        } else {
            registPresenter = new RegistPresenter(this);
        }

        final EditText birthDayEdit = (EditText)findViewById(R.id.birthdayEditText);
        Calendar now = Calendar.getInstance();
        final int year = now.get(Calendar.YEAR);
        final int month = now.get(Calendar.MONTH);
        final int day = now.get(Calendar.DAY_OF_MONTH);

        birthDayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDayEdit.setText(String.format(Locale.JAPAN,"%04d/%02d/%02d", year, month, dayOfMonth));
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        Button registButton = (Button)findViewById(R.id.inputFinishButton);
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goConfirm();
            }
        });
    }

    private void sendDataToPresenter(){
        EditText userIdEdit = (EditText) findViewById(R.id.loginIdEditText);
        registPresenter.setUserId(userIdEdit.getText().toString());

        EditText passwordEdit = (EditText) findViewById(R.id.passwordEditText);
        registPresenter.setPassword(passwordEdit.getText().toString());

        RadioGroup genderRadio = (RadioGroup) findViewById(R.id.genderRadioGroup);
        int genderId = genderRadio.getCheckedRadioButtonId();

        if(genderId == R.id.maleRadio){
            registPresenter.setMale();
        } else if (genderId == R.id.famaleRadio) {
            registPresenter.setFemale();
        }

        Spinner prefectureSpinner = (Spinner) findViewById(R.id.prefectureSpinner);
        registPresenter.setPrefecture((String)prefectureSpinner.getSelectedItem());

        EditText birthDayEdit = (EditText) findViewById(R.id.birthdayEditText);
        registPresenter.setBirthDay(birthDayEdit.getText().toString());
    }

    @Override
    public void showErrorMessage(List<Integer> errorMessageIds){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessageIds.get(0));
        builder.show();
    }

    @Override
    public void goConfirmation(UserModel user){
        Intent i = new Intent(this, ConfirmationActivity.class);
        i.putExtra("user", user);
        startActivity(i);
        finish();
    }

    @Override
    public void showLoginId(String loginId) {
        EditText loginIdEdit = (EditText)findViewById(R.id.loginIdEditText);
        loginIdEdit.setText(loginId);
    }

    @Override
    public void showPassword(String password) {
        EditText passwordEdit = (EditText)findViewById(R.id.passwordEditText);
        passwordEdit.setText(password);
    }

    @Override
    public void showAsFemale() {
        RadioButton female = (RadioButton) findViewById(R.id.famaleRadio);
        female.setChecked(true);
    }

    @Override
    public void showAsMale() {
        RadioButton male = (RadioButton) findViewById(R.id.maleRadio);
        male.setChecked(true);
    }

    @Override
    public void showPrefecture(String prefecture) {
        Spinner spinner = (Spinner)findViewById(R.id.prefectureSpinner);
        List<String> prefectures = Arrays.asList(getResources().getStringArray(R.array.prefectures));
        int selection = prefectures.indexOf(prefecture);
        spinner.setSelection(selection);
    }

    @Override
    public void showBirthday(Date birthDay){
        EditText birthDayText = (EditText)findViewById(R.id.birthdayEditText);
        String birthDayAsString = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN).format(birthDay);
        birthDayText.setText(birthDayAsString);
    }
    private void goConfirm(){

        sendDataToPresenter();

        registPresenter.validate();
    }
}
