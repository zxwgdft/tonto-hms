package com.tonto.hms.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonto.hms.dao.UserDao;
import com.tonto.hms.model.User;
import com.tonto.hms.service.ServiceResult;
import com.tonto.hms.service.UserSession;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public ServiceResult login(String username, String password) {
		User user=userDao.getUser(username);
		if(user==null||!user.getPassword().equals(password))
		{
			return new ServiceResult(false,"用户不存在或密码错误");
		}
		
		UserSession userSession=UserSession.getLocalUserSession();
		userSession.setUserName(user.getUserName());
		userSession.setNickName(user.getNickName());
		
		
		
		return new ServiceResult(true,"登录成功");
	}

}
