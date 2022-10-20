package com.bilgeadam.SpringBootRestHibernate.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernate.jparepository.OgretmenRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ogretmen;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OgretmenService
{
	private OgretmenRepository jpaRepo;

	public List<Ogretmen> findByName(String name)
	{
		return jpaRepo.findAllByNAMELikeOrderByIDDesc("%" + name + "%");
	}

	public boolean deleteById(Long id)
	{
		try
		{
			jpaRepo.deleteById(id);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(Ogretmen ogretmen)
	{
		try
		{
			return jpaRepo.save(ogretmen) != null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public Ogretmen getById(Long id)
	{
		// try catch yazmadan if 'ler ile yönetilebilir
		return jpaRepo.findById(id).get();
	}

	public List<Ogretmen> getAll()
	{
		return jpaRepo.findAll(Sort.by("ID").descending());
	}
}