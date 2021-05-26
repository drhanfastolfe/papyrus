package com.papyrus.prestamo;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Prestamo> findAll(Sort sort, String nombreSocio)
	{
		List<Socio> listaSocios = new ArrayList<>();
		List<Prestamo> listaPrestamos = new ArrayList<>();
		 
		if(nombreSocio != null)
		{
			listaSocios = socioService.searchByNombreApellidos(nombreSocio);

			for (Socio socio : listaSocios)
			{
				listaPrestamos.addAll(socio.getListaPrestamos());	
			}
		}
		else
		{
			listaPrestamos = repo.findAll();
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
