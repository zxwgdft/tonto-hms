package com.tonto.hms.ums.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.tonto.hms.service.UserSession;
import com.tonto.hms.util.WebUtil;
import com.tonto.hms.web.control.JsonResponse;

public class PermissionFilter extends AuthorizationFilter {
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject=SecurityUtils.getSubject();
		UserSession usersession=(UserSession) subject.getPrincipal();		
		String path=((HttpServletRequest) request).getServletPath();	
		return usersession.getRole().hasActionPermisson(path);	
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws IOException {
		
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		
		if (WebUtil.isAjaxRequest(httpRequest)) {
			WebUtil.sendJson((HttpServletResponse) response,
					JsonResponse.getNoPermissionResponse("没有权限访问"));
		}
		else
		{
			((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath()+"/html/noPermission.html");			
		}	
		return false;
	}

}
