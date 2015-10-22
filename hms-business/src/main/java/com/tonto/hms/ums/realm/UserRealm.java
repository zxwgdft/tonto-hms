package com.tonto.hms.ums.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tonto.hms.dao.UserDao;
import com.tonto.hms.model.User;
import com.tonto.hms.service.UserSession;
import com.tonto.hms.ums.permission.Role;
import com.tonto.hms.ums.permission.action.PermissionManager;

public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	PermissionManager permissionManager;
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
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
				
				Integer roleId=user.getRoleId();
				Role role=permissionManager.getRole(roleId);
				usersession.setRole(role);
				
				return new SimpleAuthenticationInfo(usersession,user.getPassword(),getName());
			}
		}
		return null;
	}

}
