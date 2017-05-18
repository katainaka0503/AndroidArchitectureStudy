package com.example.michihiroyamasaki.sample.dao;

import android.content.Context;

import com.example.michihiroyamasaki.sample.model.GoodsModel;
import com.example.michihiroyamasaki.sample.presenter.GoodsDao;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import static com.j256.ormlite.android.apptools.OpenHelperManager.getHelper;

public class GoodsDaoImpl implements GoodsDao {

    private Dao<GoodsModel, Long> goodsDaoRaw;
    public GoodsDaoImpl(Context context) throws SQLException {
        goodsDaoRaw = getHelper(context, AppDatabaseHelper.class).getDao(GoodsModel.class);
    }

    @Override
    public List<GoodsModel> findAll() throws SQLException {
        return goodsDaoRaw.queryForAll();
    }
}
