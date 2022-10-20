package com.bilgeadam.SpringBootRestHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.bilgeadam.SpringBootRestHibernate.model.Ders;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class DersRepo
{
	private EntityManager entityManager;

	public Ders save(Ders ders)
	{
		entityManager.persist(ders);
		return ders;
	}

	public List<Ders> getAll()
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ders> criteriaQuery = builder.createQuery(Ders.class);
		Root<Ders> root = criteriaQuery.from(Ders.class);
		criteriaQuery.select(root).orderBy(builder.desc(root.get("ID")));
		TypedQuery<Ders> myQuery = entityManager.createQuery(criteriaQuery);
		return myQuery.getResultList();
	}

	public Ders getByIdCriteria(Long id)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ders> criteriaQuery = builder.createQuery(Ders.class);
		Root<Ders> root = criteriaQuery.from(Ders.class);
		ParameterExpression<Number> params = builder.parameter(Number.class);
		criteriaQuery.select(root).where(builder.equal(root.get("ID"), params));
		TypedQuery<Ders> myQuery = entityManager.createQuery(criteriaQuery);
		myQuery.setParameter(params, id);
		return myQuery.getSingleResult();
	}

	public Ders getById(Long id)
	{
		return entityManager.find(Ders.class, id);
	}

	public Ders update(Ders ders)
	{
		return entityManager.merge(getById(ders.getID()));
	}

	public void deleteById(Long id)
	{
		entityManager.remove(getById(id));
	}

}