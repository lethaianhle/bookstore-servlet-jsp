package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	
	private static final String[] loginRequiredURLs = {
			"/view_profile", "/edit_profile", "/update_profile", "/write_review", "checkout", "place_order"
	};

    public CustomerLoginFilter() {
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		if(path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		
		String requestURL = httpRequest.getRequestURL().toString();
		boolean loggedIn = session!= null && session.getAttribute("loggedCustomer") != null;
		
		if(!loggedIn && isLoginRequired(requestURL)) {
			StringBuilder redirectURL = new StringBuilder(requestURL);
			redirectURL.append("?");
			redirectURL.append(httpRequest.getQueryString());
			
			session.setAttribute("redirectURL", redirectURL.toString());
			
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("frontend/login.jsp");
			dispatcher.forward(request, response);
			
		} else {
			chain.doFilter(request, response);
		}

	}
	
	private boolean isLoginRequired(String requestURL) {
		for (String string : loginRequiredURLs) {
			if(requestURL.contains(string)) {
				return true;
			}
		}
		return false;
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
