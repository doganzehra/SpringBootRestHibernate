package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;

@Service
@Transactional
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long>
{
	@Query(name = "findByOgrenciName", value = "SELECT * FROM obsh.ogrenci WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Ogrenci> findByOgrenciName(@Param("NAME") String name);

	public List<Ogrenci> findAllByNAMELike(String name);

	public List<Ogrenci> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort);

	// select * from Ogrenci where NAME like 'asd' order by id desc
	public List<Ogrenci> findAllByNAMELikeOrderByIDDesc(String name);
}
