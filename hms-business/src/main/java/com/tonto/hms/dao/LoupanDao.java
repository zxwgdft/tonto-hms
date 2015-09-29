package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.Loupan;

public interface LoupanDao extends BaseDao<Integer,Loupan>{
 
    List<Loupan> findAll();

	List<Loupan> searchAll(Loupan loupan);
}