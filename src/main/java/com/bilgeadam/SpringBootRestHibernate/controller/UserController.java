package com.bilgeadam.SpringBootRestHibernate.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.SpringBootRestHibernate.model.CustomUser;
import com.bilgeadam.SpringBootRestHibernate.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController
{
	private UserService userService;

	@PostMapping(path = "/user/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestBody CustomUser user)
	{
		// {"username":"numan", "password":"1234"}
		if (userService.save(user))
		{
			return ResponseEntity.ok("Başarı ile kaydedildi");
		}
		else
		{
			return ResponseEntity.internalServerError().body("Başarısız");
		}
	}
}