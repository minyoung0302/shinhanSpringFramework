package com.shinhan.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinhan.myapp.vo.MemberDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @WebFilter : Servlet3버전부터 지원한다
 */

@Slf4j
@WebFilter("/LoginCheckFilter")
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청 수행하기 전
		HttpServletRequest req = (HttpServletRequest)request;
		//요청의 주소를 얻어보기
		String contextPath = request.getServletContext().getContextPath();
		String uri = req.getRequestURI();
		uri = uri.substring(contextPath.length());
		log.info("contextPath" + contextPath);
		log.info("요청의 주소를 얻어보기" + uri);
		
		//요청 주소가 로그인이면 요청대로 수행하고 로그인이 아니면 로그인 여부 체크
		if(!uri.equals("/auth/login.do") && !uri.contains("/rest")&&!uri.contains("/chat")) {
			HttpSession session = req.getSession();
			MemberDTO member = (MemberDTO)session.getAttribute("loginMember");
			if(member == null) {
				log.info("로그인 안함");
				HttpServletResponse res = (HttpServletResponse)response;
				res.sendRedirect(contextPath + "/auth/login.do");
				return;
			}
		}
		
		
		
		chain.doFilter(request, response);
		//요청 수행 후 - 응답 전
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
