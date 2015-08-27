package com.tonto.hms.ums.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tonto.hms.dao.UserDao;
import com.tonto.hms.model.User;
import com.tonto.hms.service.UserSession;

public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	UserDao userDao;
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 String username = (String) principals.fromRealm( 
		         getName()).iterator().next(); 
		      
	      if( username != null ){ 
	            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); 
	            info.addRole("admin");
	            return info; 
	      } 
	      
	      return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username=((UsernamePasswordToken)token).getUsername();
		if(username!=null&&!"".equals(username))
		{
			User user=userDao.getUser(username);
			if(user!=null)
			{
				UserSession usersession=new UserSession();
				usersession.setNickName(user.getNickName());
				usersession.setUserName(user.getUserName());
				return new SimpleAuthenticationInfo(usersession,user.getPassword(),getName());
			}
		}
		return null;
	}

}
