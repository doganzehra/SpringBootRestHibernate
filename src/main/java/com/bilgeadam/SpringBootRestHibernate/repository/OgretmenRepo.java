package com.bilgeadam.SpringBootRestHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.bilgeadam.SpringBootRestHibernate.model.Ogretmen;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
// javax.transactional değil !!!
@org.springframework.transaction.annotation.Transactional
public class OgretmenRepo
{
	private EntityManager em;

//	@PostConstruct
//	public void info()
//	{
//		// ayağa kalkarken hangi entitymanager inject edildi?
//		System.err.println(em.getClass());
//	}

	public Ogretmen save(Ogretmen ogretmen)
	{
		em.persist(ogretmen);
		return ogretmen;
	}

	public List<Ogretmen> getAll()
	{
		// bu aşama criteria tanımlar
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ogretmen> criteriaQuery = builder.createQuery(Ogretmen.class);
		// select * from ogretmen order by id desc
		Root<Ogretmen> root = criteriaQuery.from(Ogretmen.class);
		criteriaQuery.select(root).orderBy(builder.desc(root.get("ID")));
		// bu aşama sql oluşturur
		TypedQuery<Ogretmen> myQuery = em.createQuery(criteriaQuery);
		// bu aşama sql çalıştırır
		return myQuery.getResultList();
	}

	public Ogretmen getByIdCriteria(Long id)
	{
		// bu aşama criteria tanımlar
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ogretmen> criteriaQuery = builder.createQuery(Ogretmen.class);
		// ---------------------
		// select * from ogretmen where ogretmen.id = 1
		Root<Ogretmen> root = criteriaQuery.from(Ogretmen.class);
		ParameterExpression<Number> params = builder.parameter(Number.class);
		criteriaQuery.select(root).where(builder.equal(root.get("ID"), params));
		// ---------------------
		// bu aşama sql oluşturur
		TypedQuery<Ogretmen> myQuery = em.createQuery(criteriaQuery);
		myQuery.setParameter(params, id);
		// bu aşama sql çalıştırır
		return myQuery.getSingleResult();
	}

	public List<Ogretmen> findByName(String name)
	{
		// bu aşama criteria tanımlar
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ogretmen> criteriaQuery = builder.createQuery(Ogretmen.class);
		Root<Ogretmen> root = criteriaQuery.from(Ogretmen.class);
		// ---------------------
		// select * from ogretmen where ogretmen.name like '%ma%'
		ParameterExpression<String> params = builder.parameter(String.class);
		criteriaQuery.select(root).where(builder.like(root.get("NAME"), params));
		// ---------------------
		// bu aşama sql oluşturur
		TypedQuery<Ogretmen> myQuery = em.createQuery(criteriaQuery);
		myQuery.setParameter(params, "%" + name + "%");
		// bu aşama sql çalıştırır
		return myQuery.getResultList();
	}

	public Ogretmen getById(Long id)
	{
		return em.find(Ogretmen.class, id);
	}

	public Ogretmen update(Ogretmen ogretmen)
	{
		return em.merge(getById(ogretmen.getID()));
		// em.merge(ogretmen);
	}

	public void deleteById(Long id)
	{
		em.remove(getById(id));
		// bu metod çalışmaz ve detached entity hatası verir
		// Ogretmen ogr = new Ogretmen(1L, "Numan", false, new HashSet<>());
		// em.remove(ogr);
	}
}