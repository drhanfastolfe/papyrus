package com.papyrus.libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public List<Libro> librosMasPrestados()
	{
		List<Libro> librosMasPrestados = repo.findAll();

		Comparator<Libro> comparaPrestamos = (Libro l1, Libro l2) -> Integer.valueOf(l1.prestamosCount()).compareTo(Integer.valueOf(l2.prestamosCount())); 

		Collections.sort(librosMasPrestados, comparaPrestamos.reversed());

		if(librosMasPrestados.size() > 5)
		{
			librosMasPrestados = librosMasPrestados.subList(0, 5);
		}

		return librosMasPrestados;
	}

	public List<Libro> librosMasEjemplares()
	{
		List<Libro> librosMasEjemplares = findAll();

		Comparator<Libro> comparaEjemplares = (Libro l1, Libro l2) -> Integer.valueOf(l1.getListaEjemplares().size()).compareTo(Integer.valueOf(l2.getListaEjemplares().size())); 

		Collections.sort(librosMasEjemplares, comparaEjemplares.reversed());

		if(librosMasEjemplares.size() > 5)
		{
			librosMasEjemplares = librosMasEjemplares.subList(0, 5);
		}

		return librosMasEjemplares;
	}
}
