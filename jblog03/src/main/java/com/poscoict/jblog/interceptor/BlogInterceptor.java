package com.poscoict.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.BlogService;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		String blogId = uri.split("/")[2];
		
		if (blogService.getBlog(blogId) == null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true;
	}

}
