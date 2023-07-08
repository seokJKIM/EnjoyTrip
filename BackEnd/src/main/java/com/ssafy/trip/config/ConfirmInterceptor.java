package com.ssafy.trip.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.trip.user.exception.UnAuthorizedException;
import com.ssafy.trip.user.model.User;
import com.ssafy.trip.user.model.service.JwtService;


//@SuppressWarnings("deprecation")
//public class ConfirmInterceptor extends HandlerInterceptorAdapter {
//spring 5.3 부터는 HandlerInterceptor implements
@Component
public class ConfirmInterceptor implements HandlerInterceptor { 

	//public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	private static final String HEADER_AUTH = "auth-token";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(HEADER_AUTH);

		if (token != null && jwtService.checkToken(token)) {
			//logger.info("토큰 사용 가능 : {}", token);
			return true;
		} else {
			//logger.info("토큰 사용 불가능 : {}", token);
			throw new UnAuthorizedException();
		}

	}
	
	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("userInfo");
//		if(user == null) {
//			response.sendRedirect(request.getContextPath() + "/");
//			return false;
//		}
//		return true;
//	}
	
}