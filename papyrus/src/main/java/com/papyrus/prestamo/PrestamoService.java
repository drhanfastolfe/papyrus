package com.papyrus.prestamo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.empleado.Empleado;
import com.papyrus.empleado.EmpleadoService;
import com.papyrus.libro.Libro;
import com.papyrus.libro.LibroService;
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
	private LibroService libroService;
	
	public List<Prestamo> findAll(Sort sort, String keyword, boolean activo)
	{
		List<Socio> listaSocios = new ArrayList<>();
		List<Empleado> listaEmpleados = new ArrayList<>();
		List<Libro> listaLibros = new ArrayList<>();
		Set<Prestamo> setPrestamos = new LinkedHashSet<>();
		List<Prestamo> listaPrestamos = new ArrayList<>();
		 
		if(keyword != null)
		{
			listaSocios = socioService.search(keyword);
			listaEmpleados = empleadoService.search(keyword);
			listaLibros = libroService.search(keyword);

			for (Socio socio : listaSocios)
			{
				setPrestamos.addAll(socio.getListaPrestamos());	
			}

			for (Empleado empleado : listaEmpleados)
			{
				setPrestamos.addAll(empleado.getListaPrestamos());	
			}

			for (Libro libro : listaLibros)
			{
				for (Ejemplar ejemplar : libro.getListaEjemplares())
				{
					setPrestamos.addAll(ejemplar.getListaPrestamos());	
				}
			}

			List<Prestamo> listaPrestamosSet = new ArrayList<>(setPrestamos);
			listaPrestamos = listaPrestamosSet;
		}
		else
		{
			listaPrestamos = repo.findAll();
		}

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
