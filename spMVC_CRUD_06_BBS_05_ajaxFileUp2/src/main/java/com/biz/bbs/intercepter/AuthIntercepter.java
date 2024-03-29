package com.biz.bbs.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.biz.bbs.model.MemberVO;
import com.biz.bbs.service.BbsService;
import com.biz.bbs.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthIntercepter extends HandlerInterceptorAdapter{
	
	/*
	 * Service에 있는 method를 사용해서 어떤 기능을 수행하고 싶을때는
	 * Controller에서 사용하는 방법으로 구현이 가능하다
	 */
	@Autowired
	BbsService bService;
	
	@Autowired
	MemberService mService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		/*
		 * login등 처리를 하기 위해서는 httpSession클래스를 사용해야 하는데
		 * Controller에서 처럼 매개변수로 처리하여 사용이 불가능하다
		 * request의 getSession() 메서드를 사용하여 session정보를 추출
		 */
		HttpSession httpSession = request.getSession();
		MemberVO memberVO = (MemberVO) httpSession.getAttribute("LOGIN");
		if(memberVO == null) {
			response.sendRedirect("/bbs/member/login?LOGIN_MSG=LOGIN");
			//Controller로 제어권 전달하지 않고 종료
			return false;
		}
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI().toString();
		
		log.debug("URL :" + url);
		log.debug("URI :" + uri);
		// 제어권을 Controller에게 전달
		return super.preHandle(request, response, handler);
	}

}
