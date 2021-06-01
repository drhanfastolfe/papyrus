package com.papyrus.libro;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.papyrus.autor.Autor;
import com.papyrus.autor.AutorService;
import com.papyrus.categoria.Categoria;
import com.papyrus.categoria.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;
	
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

	public List<Libro> search(String keyword)
	{
		List<Libro> listaLibros = new ArrayList<>();
		Set<Libro> setLibros = new LinkedHashSet<>();

		setLibros.addAll(repo.search(keyword));

		for (Autor autor : autorService.search(keyword))
		{
			setLibros.addAll(autor.getListaLibros());	
		}

		for (Categoria categoria : categoriaService.search(keyword))
		{
			setLibros.addAll(categoria.getListaLibros());	
		}

		List<Libro> listaLibrosSet = new ArrayList<>(setLibros);
		listaLibros = listaLibrosSet;

		return listaLibros;
	}
}
