package jp.co.sss.cytech.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		if (uri.contains("/user/login") || uri.contains("/user/register") || uri.contains("/css") || uri.contains("/images")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (session.getAttribute("loginUserId") == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	

}
