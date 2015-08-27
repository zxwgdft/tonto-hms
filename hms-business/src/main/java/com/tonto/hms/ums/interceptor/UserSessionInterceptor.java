package com.tonto.hms.ums.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tonto.hms.service.UserSession;

public class UserSessionInterceptor implements HandlerInterceptor{
	
	private static final String USER_SESSION="usersession";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session=request.getSession();
		UserSession userSession=(UserSession) session.getAttribute(USER_SESSION);
		if(userSession==null)
		{
			userSession=new UserSession();
			session.setAttribute(USER_SESSION, userSession);
		}
		UserSession.setLocalUserSession(userSession);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
