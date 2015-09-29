package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.House;
import com.tonto.hms.model.HouseDetail;

public interface HouseDao extends BaseDao<Integer,House>{
    
    List<House> findAll();
    
    List<House> searchAll(House house);
    
    List<HouseDetail> searchDetailAll(HouseDetail houseDetail);
}