package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bilgeadam.SpringBootRestHibernate.model.Ders;

public interface DersRepository extends JpaRepository<Ders, Long>, JpaSpecificationExecutor<Ders>
{
	// select * from ders where ders.ogretmen.name = 'salim'
	// select * from obsh.ders D where (select O.NAME from obsh.ogretmen AS O where
	// D."ogretmen_id" = O."id") = 'Salim'
	// _ ile tablodan tabloya atlayanilirim
	public List<Ders> findAllDersByogretmen_NAMELike(String name);

	public List<Ders> findAllDersBykonu_NAMELike(String name);
}