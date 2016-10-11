package com.tonto.hms.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonto.hms.dao.UserDao;
import com.tonto.hms.service.ServiceResult;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public ServiceResult login(String username, String password) {

		
		return new ServiceResult(true,"登录成功");
	}

}
