package com.tonto.hms.dao;

import com.tonto.hms.model.Constant;

public interface ConstantDao {
    int deleteByPrimaryKey(Integer keyId);

    int insert(Constant record);

    int insertSelective(Constant record);

    Constant selectByPrimaryKey(Integer keyId);

    int updateByPrimaryKeySelective(Constant record);

    int updateByPrimaryKey(Constant record);
}