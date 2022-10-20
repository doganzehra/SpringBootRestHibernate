package com.bilgeadam.SpringBootRestHibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Entity
@Getter
public class Role implements GrantedAuthority
{
	private static final long serialVersionUID = 9156064156119386503L;

	@Id
	private String name;

	@Override
	public String getAuthority()
	{
		return name;
	}
}