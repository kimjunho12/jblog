package com.poscoict.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. HandlerMethod의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. HandlerMethod에 @Auth가 있는지 확인
		if (auth == null) {
			return true;
		}

		// 5. @Auth가 적용이 되어있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 6. 권한(Authorization) 체크를 위해 현재 페이지의 blogId를 확인
		String uri = request.getRequestURI();
		String blogId = uri.split("/")[2];
		if (blogId.equals(authUser.getId())) {
			return true;
		}

		// 7. 최종 인증 실패 원래 페이지로 돌아감
		response.sendRedirect(request.getContextPath() + "/" + blogId);
		return false;
	}

}
