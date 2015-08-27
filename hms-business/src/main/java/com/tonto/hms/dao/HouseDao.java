package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.model.House;
import com.tonto.hms.model.HouseDetail;

public interface HouseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    
    List<House> findAll();
    
    List<House> searchAll(House house);
    
    List<HouseDetail> searchDetailAll(HouseDetail houseDetail);
}