package com.tonto.hms.dao;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.User;

public interface UserDao extends BaseDao<Integer,User>{	
	User getUser(String username);
}