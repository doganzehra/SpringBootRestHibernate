package com.bilgeadam.SpringBootRestHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
@Transactional
public class OgrenciRepo
{
	private EntityManager entityManager;

	public Ogrenci save(Ogrenci ogrenci)
	{
		entityManager.persist(ogrenci);
		return ogrenci;
	}

	public List<Ogrenci> getAll()
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ogrenci> criteriaQuery = builder.createQuery(Ogrenci.class);
		Root<Ogrenci> root = criteriaQuery.from(Ogrenci.class);
		criteriaQuery.select(root).orderBy(builder.desc(root.get("ID")));
		TypedQuery<Ogrenci> myQuery = entityManager.createQuery(criteriaQuery);
		return myQuery.getResultList();
	}

	public Ogrenci getByIdCriteria(Long id)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ogrenci> criteriaQuery = builder.createQuery(Ogrenci.class);
		Root<Ogrenci> root = criteriaQuery.from(Ogrenci.class);
		ParameterExpression<Number> params = builder.parameter(Number.class);
		criteriaQuery.select(root).where(builder.equal(root.get("ID"), params));
		TypedQuery<Ogrenci> myQuery = entityManager.createQuery(criteriaQuery);
		myQuery.setParameter(params, id);
		return myQuery.getSingleResult();
	}

	public List<Ogrenci> findByName(String name)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ogrenci> criteriaQuery = builder.createQuery(Ogrenci.class);
		Root<Ogrenci> root = criteriaQuery.from(Ogrenci.class);
		ParameterExpression<String> params = builder.parameter(String.class);
		criteriaQuery.select(root).where(builder.like(root.get("NAME"), params));
		TypedQuery<Ogrenci> myQuery = entityManager.createQuery(criteriaQuery);
		myQuery.setParameter(params, "%" + name + "%");
		return myQuery.getResultList();
	}

	public Ogrenci getById(Long id)
	{
		return entityManager.find(Ogrenci.class, id);
	}

	public Ogrenci update(Ogrenci ogrenci)
	{
		return entityManager.merge(getById(ogrenci.getID()));
	}

	public void deleteById(Long id)
	{
		entityManager.remove(getById(id));
	}

}