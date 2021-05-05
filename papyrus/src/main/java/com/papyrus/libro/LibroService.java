package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;
	
	public List<Libro> listarTodo()
	{
		return repo.findAll();
	}
	
	public void insertar(Libro libro)
	{
		repo.save(libro);
	}
	
	public Libro obtener(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void borrar(Long id)
	{
		repo.deleteById(id);
	}
}
