package com.papyrus.libro;

import java.util.ArrayList;
import java.util.List;

import com.papyrus.editorial.EditorialService;
import com.papyrus.libro_editorial.LibroEditorial;
import com.papyrus.libro_editorial.LibroEditorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;

	@Autowired
	private EditorialService editorialService;

	@Autowired
	private LibroEditorialService libroEditorialService;
	
	public List<Libro> findAll()
	{
		return repo.findAll();
	}
	
	public void add(Libro libro)
	{
		repo.save(libro);
	}
	
	public Libro get(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void delete(Long id)
	{
		repo.deleteById(id);
	}

	public List<Libro> findByEditorialNombre(String editorialNombre)
	{
		List<LibroEditorial> libroEditoriales;
		List<Libro> libros = new ArrayList<>();

		libroEditoriales = editorialService.findByNombre(editorialNombre).getLibroEditoriales();
		
		for (LibroEditorial libroEditorial : libroEditoriales)
		{
			libros.add(libroEditorial.getLibro());
		}		

		return libros;
	}
}
