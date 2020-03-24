package com.andon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

/**
 * Servlet Filter implementation class SessionFilter
 */
@Component
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
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
		HttpServletRequest req = (HttpServletRequest) request;
		 
		HttpServletResponse res = (HttpServletResponse) response;
 
		// 获得请求的URL
		String url = req.getRequestURL().toString();
 
		// 获得session中的对象
		HttpSession session = req.getSession();
		Object user = session.getAttribute("username");
		
		if(user != null){
			chain.doFilter(request, response);
		}else{
			if(url.contains("loginIn.do")){
				chain.doFilter(request, response);
				return;
			}
			res.getWriter().write("{\"ret\": \"3\"}");

//			res.sendRedirect(req.getContextPath() + "/metronic/login.html");
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
