package com.papyrus.empleado;

import java.util.Collections;
import java.util.Comparator;
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

	public Empleado findByUsuario(String usuario)
	{
		return repo.findByUsuario(usuario);
	}

	public List<Empleado> search(String keyword)
	{
		return repo.search(keyword);
	}

	public List<Empleado> empleadosMasActivos()
	{
		List<Empleado> empleadosMasActivos = repo.findAll();

		Comparator<Empleado> comparaPrestamos = (Empleado e1, Empleado e2) -> Integer.valueOf(e1.getListaPrestamos().size()).compareTo(Integer.valueOf(e2.getListaPrestamos().size())); 

		Collections.sort(empleadosMasActivos, comparaPrestamos.reversed());

		if(empleadosMasActivos.size() > 5)
		{
			empleadosMasActivos = empleadosMasActivos.subList(0, 5);
		}

		return empleadosMasActivos;
	}
}
