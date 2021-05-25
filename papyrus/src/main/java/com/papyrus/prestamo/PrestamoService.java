package com.papyrus.prestamo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService
{
    @Autowired
	private PrestamoRepository repo;
	
	public List<Prestamo> findAll(Sort sort)
	{
		return repo.findAll();
	}
	
	public void save(Prestamo prestamo)
	{
		repo.save(prestamo);
	}
	
	public Prestamo findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	}
}
