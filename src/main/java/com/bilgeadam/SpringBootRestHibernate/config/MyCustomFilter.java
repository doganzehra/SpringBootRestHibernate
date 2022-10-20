package com.bilgeadam.SpringBootRestHibernate.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// @Component
// @org.springframework.core.annotation.Order(value = 200)
// @WebFilter() // veya web.xml 'de olacak
public class MyCustomFilter implements javax.servlet.Filter
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		// ------------------- ÖRNEK ------------------
		// String username = ((HttpServletRequest) request).getHeader("username");
		// String password = ((HttpServletRequest) request).getHeader("password");
		// userrepo.checkusernamepassword(username, password);
		// ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
		// ((HttpServletResponse) response).getWriter().append("Kullanıcı adı şifre yanlış");
		// ------------------- ÖRNEK ------------------
		// System.err.println("my custom filter çalışıyor");
		chain.doFilter(request, response);
	}
}