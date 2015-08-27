package com.tonto.hms.exception;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.tonto.hms.web.control.Response;

public class ExceptionHandler extends ExceptionHandlerExceptionResolver{
	
	private String errorJson="{\"status\":"+Response.STATUS_ERROR+",\"msg\":\"?\"}";

	private String defaultErrorView;  
	  
    public String getDefaultErrorView() {  
        return defaultErrorView;  
    }  
  
    public void setDefaultErrorView(String defaultErrorView) {  
        this.defaultErrorView = defaultErrorView;  
    }  
	
	@Override
	protected ModelAndView doResolveHandlerMethodException(
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception ex) {
		
		
		if (handlerMethod == null) {  
            return null;  
        }  
  
        Method method = handlerMethod.getMethod();  
  
        if (method == null) {  
            return null;  
        }  
        
		ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(
				method, ResponseBody.class);
		if (responseBodyAnn != null) {
			try {
				ResponseStatus responseStatusAnn = AnnotationUtils
						.findAnnotation(method, ResponseStatus.class);
				if (responseStatusAnn != null) {
					HttpStatus responseStatus = responseStatusAnn.value();
					String reason = responseStatusAnn.reason();
					if (!StringUtils.hasText(reason)) {
						response.setStatus(responseStatus.value());
					} else {
						try {
							response.sendError(responseStatus.value(),
									reason);
						} catch (IOException e) {
						}
					}
				}

				response.setContentType("application/json; charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(errorJson.replace("?",ex.getMessage()));
				return new ModelAndView();
			} catch (Exception e) {
			}
		}
		else
		{
			
			return new ModelAndView("error/error","error",ex.getMessage());
		}
		
		return null;
	}



}
