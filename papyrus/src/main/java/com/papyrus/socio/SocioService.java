package com.papyrus.socio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioService
{
    @Autowired
	private SocioRepository repo;
	
	public List<Socio> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Socio socio)
	{
		repo.save(socio);
	}
	
	public Socio findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	}

	public Socio findByDni(String dni)
	{
		return repo.findByDni(dni);
	}

	public List<Socio> searchByNombreApellidos(String nombreApellidos)
	{
		return repo.searchByNombreApellidos(nombreApellidos);
	}

	public List<Socio> search(String keyword)
	{
		return repo.search(keyword);
	}

	public List<Socio> blackList()
	{
		List<Socio> blackList = new ArrayList<>();
		List<Socio> listaSocios = repo.findAll();

		for (Socio socio : listaSocios)
		{
			if (socio.retrasos() > 0) blackList.add(socio);	
		}
		
		Comparator<Socio> comparaRetrasos = (Socio s1, Socio s2) -> Integer.valueOf(s1.retrasos()).compareTo(Integer.valueOf(s2.retrasos())); 

		Collections.sort(blackList, comparaRetrasos.reversed());
		
		return blackList;
	}

	public List<Socio> sociosMasActivos()
	{
		List<Socio> sociosMasActivos = repo.findAll();

		Comparator<Socio> comparaPrestamos = (Socio s1, Socio s2) -> Integer.valueOf(s1.getListaPrestamos().size()).compareTo(Integer.valueOf(s2.getListaPrestamos().size())); 

		Collections.sort(sociosMasActivos, comparaPrestamos.reversed());

		if(sociosMasActivos.size() > 5)
		{
			sociosMasActivos = sociosMasActivos.subList(0, 5);
		}

		return sociosMasActivos;
	}
}
