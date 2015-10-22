package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.AcRole;

public interface RoleDao extends BaseDao<Integer,AcRole>{

	List<AcRole> findAll();
}