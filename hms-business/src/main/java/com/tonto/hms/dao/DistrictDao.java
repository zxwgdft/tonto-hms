package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.model.District;

public interface DistrictDao {
    int deleteByPrimaryKey(Integer code);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
    
    List<District> findAll();
}