package com.bilgeadam.SpringBootRestHibernate.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

//yetkile kontrolü
public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
	public JWTAuthorizationFilter(AuthenticationManager authManager)
	{
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		String token = req.getHeader("Authorization");
		// Bearer 30948hgb57gbhg9wpuısgh==
		UsernamePasswordAuthenticationToken authentication = null;
		if (token != null)
		{
			try
			{
				String user = JWT.require(Algorithm.HMAC512("MY_SECRET_KEY".getBytes())).build().verify(token.replace("Bearer ", "")).getSubject();
				if (user != null)
				{
					authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			}
			catch (Exception e)
			{
			}
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
}