package com.tonto.hms.ums.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.tonto.hms.service.UserSession;

public class PermissionFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject=SecurityUtils.getSubject();
		UserSession usersession=(UserSession) subject.getPrincipal();
		return true;
	}

}
