package com.bilgeadam.SpringBootRestHibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig
{
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception
	{
		// post yapabilmek için csrf disable
		http.csrf().disable();
		// sadece post işlemleri authenticated yapılabilir
		// http.authorizeRequests().antMatchers(HttpMethod.POST).authenticated();
		http.authorizeRequests().antMatchers("/user/save").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		// sadewce ADMIN rolündekiler girebilsin istersek
		// http.authorizeRequests().antMatchers("/login").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();// .and().formLogin().loginProcessingUrl("/login");
		http.addFilter(new JWTAuthenticationFilter(auth.getObject()));
		http.addFilter(new JWTAuthorizationFilter(auth.getObject()));
		return http.build();
	}

	// extends websecurityconfigurereadapter yaptığım zaman
//	@Override
//	protected void configure(HttpSecurity http) throws Exception
//	{
//	}
}