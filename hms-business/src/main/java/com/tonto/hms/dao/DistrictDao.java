package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.District;

public interface DistrictDao extends BaseDao<Integer,District>{    
    List<District> findAll();
}