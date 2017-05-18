package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.UserModel;

import java.sql.SQLException;

public interface UserDao {
    void create(UserModel newUser) throws SQLException;
    UserModel findByUserIdAndPassword(String userId, String password) throws SQLException;
}
