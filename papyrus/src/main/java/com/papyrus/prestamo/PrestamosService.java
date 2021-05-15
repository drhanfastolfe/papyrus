package com.papyrus.prestamo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamosService
{
    @Autowired
	private PrestamoRepository repo;
	
	public List<Prestamo> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Prestamo rrestamo)
	{
		repo.save(rrestamo);
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
