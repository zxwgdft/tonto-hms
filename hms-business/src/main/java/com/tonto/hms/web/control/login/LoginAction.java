package com.tonto.hms.web.control.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tonto.hms.service.UserSession;
import com.tonto.hms.service.user.UserService;
import com.tonto.hms.util.WebUtil;
import com.tonto.hms.web.control.JsonResponse;

@Controller
public class LoginAction {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView toLogin()
	{
		return new ModelAndView("login/login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		Subject subject=SecurityUtils.getSubject();
		boolean isAjax=WebUtil.isAjaxRequest(request);
		if(subject.isAuthenticated())
		{
			if (isAjax) {
				WebUtil.sendJson(response,
						JsonResponse.getSuccessResponse("成功登录"));
				return null;
			}
			else
			{
				return toMain();
			}
		}
		else
		{
			if (isAjax) {
				WebUtil.sendJson(response,
						JsonResponse
								.getUnLoginResponse("登录失败,用户名或密码错误！"));
				return null;
			}
			else
			{
				return toLogin();
			}
		}
		
	}
	
	@RequestMapping("/main")
	public ModelAndView toMain()
	{
		Subject subject=SecurityUtils.getSubject();
		UserSession usersession=(UserSession) subject.getPrincipal();
		return new ModelAndView("login/main","user",usersession);
	}
}
