package com.papyrus.libro;

import java.util.ArrayList;
import java.util.List;

import com.papyrus.detalle.Detalle;
import com.papyrus.editorial.EditorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;

	@Autowired
	private EditorialService editorialService;
	
	public List<Libro> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Libro libro)
	{
		repo.save(libro);
	}
	
	public Libro findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	}

	public List<Libro> findByEditorialNombre(String editorialNombre)
	{
		List<Detalle> listaDetalles;
		List<Libro> libros = new ArrayList<>();

		listaDetalles = editorialService.findByNombre(editorialNombre).getDetalles();
		
		for (Detalle detalle : listaDetalles)
		{
			libros.add(detalle.getLibro());
		}		

		return libros;
	}
}
