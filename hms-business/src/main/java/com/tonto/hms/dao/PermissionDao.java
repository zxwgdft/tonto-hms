package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.AcPermission;

public interface PermissionDao extends BaseDao<Integer,AcPermission>{
    
    List<AcPermission> findAll();

}