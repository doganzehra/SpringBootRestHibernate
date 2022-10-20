package com.bilgeadam.SpringBootRestHibernate.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.SpringBootRestHibernate.model.CustomUser;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<CustomUser, Long>
{
	public CustomUser findByusername(String username);
}