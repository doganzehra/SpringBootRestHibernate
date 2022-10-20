package com.bilgeadam.SpringBootRestHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.bilgeadam.SpringBootRestHibernate.model.Konu;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class KonuRepo
{
	private EntityManager entityManager;

	public Konu save(Konu konu)
	{
		entityManager.persist(konu);
		return konu;
	}

	public List<Konu> getAll()
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Konu> criteriaQuery = builder.createQuery(Konu.class);
		Root<Konu> root = criteriaQuery.from(Konu.class);
		criteriaQuery.select(root).orderBy(builder.desc(root.get("ID")));
		TypedQuery<Konu> myQuery = entityManager.createQuery(criteriaQuery);
		return myQuery.getResultList();
	}

	public Konu getByIdCriteria(Long id)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Konu> criteriaQuery = builder.createQuery(Konu.class);
		Root<Konu> root = criteriaQuery.from(Konu.class);
		ParameterExpression<Number> params = builder.parameter(Number.class);
		criteriaQuery.select(root).where(builder.equal(root.get("ID"), params));
		TypedQuery<Konu> myQuery = entityManager.createQuery(criteriaQuery);
		myQuery.setParameter(params, id);
		return myQuery.getSingleResult();
	}

	public List<Konu> findByName(String name)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Konu> criteriaQuery = builder.createQuery(Konu.class);
		Root<Konu> root = criteriaQuery.from(Konu.class);
		ParameterExpression<String> params = builder.parameter(String.class);
		criteriaQuery.select(root).where(builder.like(root.get("NAME"), params));
		TypedQuery<Konu> myQuery = entityManager.createQuery(criteriaQuery);
		myQuery.setParameter(params, "%" + name + "%");
		return myQuery.getResultList();
	}

	public Konu getById(Long id)
	{
		return entityManager.find(Konu.class, id);
	}

	public Konu update(Konu konu)
	{
		return entityManager.merge(getById(konu.getID()));
	}

	public void deleteById(Long id)
	{
		entityManager.remove(getById(id));
	}

}