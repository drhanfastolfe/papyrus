package com.papyrus.prestamo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.ejemplar.EjemplarService;
import com.papyrus.empleado.Empleado;
import com.papyrus.empleado.EmpleadoService;
import com.papyrus.socio.Socio;
import com.papyrus.socio.SocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService
{
    @Autowired
	private PrestamoRepository repo;

	@Autowired
	private SocioService socioService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private EjemplarService ejemplarService;
	
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

	public List<Prestamo> search(String keyword, boolean activo)
	{
		List<Prestamo> listaPrestamos = new ArrayList<>();
		Set<Prestamo> setPrestamos = new LinkedHashSet<>();

		for (Ejemplar ejemplar: ejemplarService.search(keyword))
		{
			setPrestamos.addAll(ejemplar.getListaPrestamos());	
		}

		for (Socio socio : socioService.search(keyword))
		{
			setPrestamos.addAll(socio.getListaPrestamos());	
		}

		for (Empleado empleado : empleadoService.search(keyword))
		{
			setPrestamos.addAll(empleado.getListaPrestamos());
		}

		List<Prestamo> listaPrestamosSet = new ArrayList<>(setPrestamos);
		listaPrestamos = listaPrestamosSet;

		if(activo)
		{
			for(int i = 0; i < listaPrestamos.size(); i++)
			{
				if (!listaPrestamos.get(i).activo())
				{
					listaPrestamos.remove(i);
					i--;	
				}
			}
		}

		return listaPrestamos;
	}
}
