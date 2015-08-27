package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.model.Loupan;

public interface LoupanDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Loupan record);

    int insertSelective(Loupan record);

    Loupan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Loupan record);

    int updateByPrimaryKey(Loupan record);
    
    List<Loupan> findAll();

	List<Loupan> searchAll(Loupan loupan);
}