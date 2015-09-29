package com.tonto.hms.dao.base;

import java.io.Serializable;


public interface BaseDao<Key extends Serializable,Model> {
	
	 int deleteByPrimaryKey(Key key);

	 int insert(Model model);

	 int insertSelective(Model model);

	 Model selectByPrimaryKey(Key key);

	 int updateByPrimaryKeySelective(Model model);

	 int updateByPrimaryKey(Model model);
}
