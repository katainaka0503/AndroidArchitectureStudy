package com.example.michihiroyamasaki.sample.presenter;

import com.example.michihiroyamasaki.sample.model.GoodsModel;

import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {
    List<GoodsModel> findAll() throws SQLException;
}
