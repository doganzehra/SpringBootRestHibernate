package com.bilgeadam.SpringBootRestHibernate.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bilgeadam.SpringBootRestHibernate.model.CustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;

// yetkilendirme
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException
	{
		try
		{
			// header da olabilirdi, biz post body 'sini aldık
			CustomUser creds = new ObjectMapper().readValue(req.getInputStream(), CustomUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<GrantedAuthority>()));
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException
	{
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write("kullanıcı adı ve şifre bilgisi hatalı");
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException
	{
		// MY_SECRET_KEY önemli
		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 900000)).sign(Algorithm.HMAC512("MY_SECRET_KEY".getBytes()));
		// body = (asd) 95thıegbpw934htıejwrn34980thdgfb
		String body = "(" + ((User) auth.getPrincipal()).getUsername() + ") " + token;
		res.getWriter().write(body);
		res.getWriter().flush();
	}
}