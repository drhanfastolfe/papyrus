package com.papyrus.empleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService
{
    @Autowired
	private EmpleadoRepository repo;
	
	public List<Empleado> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Empleado empleado)
	{
		repo.save(empleado);
	}
	
	public Empleado findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	}
}
