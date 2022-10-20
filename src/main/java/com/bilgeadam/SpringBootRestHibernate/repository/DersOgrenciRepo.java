package com.bilgeadam.SpringBootRestHibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.SpringBootRestHibernate.model.DersOgrenci;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Repository
@AllArgsConstructor
@Transactional
public class DersOgrenciRepo
{
	private EntityManager entityManager;

	public DersOgrenci save(DersOgrenci dersOgrenci)
	{
		entityManager.persist(dersOgrenci);
		return dersOgrenci;
	}

	public List<DersOgrenci> getAll()
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DersOgrenci> criteriaQuery = builder.createQuery(DersOgrenci.class);
		Root<DersOgrenci> root = criteriaQuery.from(DersOgrenci.class);
		criteriaQuery.select(root).orderBy(builder.desc(root.get("ID")));
		TypedQuery<DersOgrenci> myQuery = entityManager.createQuery(criteriaQuery);
		return myQuery.getResultList();
	}

	public DersOgrenci getByIdCriteria(Long id)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DersOgrenci> criteriaQuery = builder.createQuery(DersOgrenci.class);
		Root<DersOgrenci> root = criteriaQuery.from(DersOgrenci.class);
		ParameterExpression<Number> params = builder.parameter(Number.class);
		criteriaQuery.select(root).where(builder.equal(root.get("ID"), params));
		TypedQuery<DersOgrenci> myQuery = entityManager.createQuery(criteriaQuery);
		myQuery.setParameter(params, id);
		return myQuery.getSingleResult();
	}

	public DersOgrenci getById(Long id)
	{
		return entityManager.find(DersOgrenci.class, id);
	}

	public DersOgrenci update(DersOgrenci dersOgrenci)
	{
		return entityManager.merge(getById(dersOgrenci.getID()));
	}

	public void deleteById(Long id)
	{
		entityManager.remove(getById(id));
	}

}