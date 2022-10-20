package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.SpringBootRestHibernate.model.DersOgrenci;

@Repository
@Transactional
public interface DersOgrenciRepository extends JpaRepository<DersOgrenci, Long>
{

	public List<DersOgrenci> findAllDersOgrenciByogrenci_NAMELike(String name);

	public List<DersOgrenci> findAllDersOgrenciByogrenci_ID(Long id);

	public List<DersOgrenci> findAllDersOgrenciByders_ID(Long id);
}
