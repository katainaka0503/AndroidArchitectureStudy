package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;
import com.example.michihiroyamasaki.sample.utils.FormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 登録画面のPresenter
 */
public class RegistPresenter {
    private RegistView view;
    private UserModel user = new UserModel();

    public RegistPresenter(RegistView view) {
        this.view = view;
    }

    public RegistPresenter(RegistView view, UserModel user){
        this(view);
        setUser(user);
    }

    /**
     * 入力されたユーザの情報を表示
     * @param user ユーザ
     */
    public void setUser(UserModel user){
        this.user = user;

        view.showLoginId(user.getUserId());
        view.showPassword(user.getPassword());
        if(UserModel.Gender.Male == user.getGender()){
            view.showAsMale();
        } else {
            view.showAsFemale();
        }
        view.showBirthday(user.getBirthDay());
        view.showPrefecture(user.getPrefecture());

    }

    public void setUserId(String userId){
        user.setUserId(userId);
    }

    public void setPassword(String password){
        user.setPassword(password);
    }

    public void setMale(){
        user.setGender(UserModel.Gender.Male);
    }

    public void setFemale(){
        user.setGender(UserModel.Gender.Female);
    }

    public void setPrefecture(String prefecture){
        user.setPrefecture(prefecture);
    }

    public void setBirthDay(String birthDayAsString){
        try {
            user.setBirthDay(FormatUtils.getInstance().parseDate(birthDayAsString));
        } catch (ParseException e) {
            user.setBirthDay(null);
        }
    }

    /**
     * 入力された内容が正しいかチェック
     */
    public void validate(){
        List<Integer> errors = user.validate();
        if(errors.isEmpty()){
            view.goConfirmation(user);
        } else {
            view.showErrorMessage(errors);
        }
    }
}
