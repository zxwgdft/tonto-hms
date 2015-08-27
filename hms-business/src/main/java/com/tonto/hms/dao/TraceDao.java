package com.tonto.hms.dao;

import com.tonto.hms.model.Trace;

public interface TraceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Trace record);

    int insertSelective(Trace record);

    Trace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trace record);

    int updateByPrimaryKey(Trace record);
}