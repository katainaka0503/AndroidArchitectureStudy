package com.example.michihiroyamasaki.sample.dao;

import android.content.Context;

import com.example.michihiroyamasaki.sample.model.UserModel;
import com.example.michihiroyamasaki.sample.presenter.UserDao;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.j256.ormlite.android.apptools.OpenHelperManager.getHelper;


public class UserDaoImpl implements UserDao {
    private Dao<UserModel, String> userDaoRaw;
    public UserDaoImpl(Context context) throws SQLException {
         userDaoRaw = getHelper(context, AppDatabaseHelper.class).getDao(UserModel.class);
    }

    @Override
    public void create(UserModel newUser) throws SQLException {
        userDaoRaw.create(newUser);
    }

    @Override
    public UserModel findByUserIdAndPassword(String userId, String password) throws SQLException {
        Map<String, Object> args = new HashMap<>();
        args.put("userId", userId);
        args.put("password", password);

        List<UserModel> found = userDaoRaw.queryForFieldValues(args);

        return found.isEmpty() ? null : found.get(0);
    }
}
