package com.bilgeadam.SpringBootRestHibernate.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernate.jparepository.UserRepository;
import com.bilgeadam.SpringBootRestHibernate.model.CustomUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
	private UserRepository repo;

	private PasswordEncoder passwordEncoder;

	// bunu thymeleaf için hazırladık
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// kendi user 'ımdan spring security user 'ına dönüştürüyorum
		// bu user var mı yok mu kontrolü
		CustomUser user;
//		try
//		{
		user = repo.findByusername(username);
//		}
//		catch (Exception e)
//		{
//			throw new UsernameNotFoundException("User bulunamadı");
//		}
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		// şifreli password set ediliyor
		builder.password(user.getPassword());
		builder.authorities(new ArrayList<>());
		return builder.build();
	}

	public boolean save(CustomUser user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user) != null;
	}
}