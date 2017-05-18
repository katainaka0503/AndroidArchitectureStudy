package com.example.michihiroyamasaki.sample.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.michihiroyamasaki.sample.model.GoodsModel;
import com.example.michihiroyamasaki.sample.model.OrderDetailModel;
import com.example.michihiroyamasaki.sample.model.OrderModel;
import com.example.michihiroyamasaki.sample.model.UserModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.math.BigInteger;
import java.sql.SQLException;

/**
 * データベースヘルパ
 */
public class AppDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "ormlite.sample";
    private static final int DATABASE_VERSION = 1;

    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.dropTable(connectionSource, OrderDetailModel.class, true);
            TableUtils.dropTable(connectionSource, OrderModel.class, true);
            TableUtils.dropTable(connectionSource, GoodsModel.class, true);
            TableUtils.dropTable(connectionSource, UserModel.class, true);
            TableUtils.createTable(connectionSource, UserModel.class);
            TableUtils.createTable(connectionSource, GoodsModel.class);
            TableUtils.createTable(connectionSource, OrderModel.class);
            TableUtils.createTable(connectionSource, OrderDetailModel.class);

            Dao<GoodsModel, Long>  goodsDao = getDao(GoodsModel.class);
            goodsDao.create(new GoodsModel("商品1", BigInteger.valueOf(100)));
            goodsDao.create(new GoodsModel("商品2", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品3", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品4", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品5", BigInteger.valueOf(100)));
            goodsDao.create(new GoodsModel("商品6", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品7", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品8", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品9", BigInteger.valueOf(100)));
            goodsDao.create(new GoodsModel("商品10", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品11", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品12", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品13", BigInteger.valueOf(100)));
            goodsDao.create(new GoodsModel("商品14", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品15", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品16", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品17", BigInteger.valueOf(100)));
            goodsDao.create(new GoodsModel("商品18", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品19", BigInteger.valueOf(200)));
            goodsDao.create(new GoodsModel("商品20", BigInteger.valueOf(200)));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
