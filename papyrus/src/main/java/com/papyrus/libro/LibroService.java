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
	
	public void agregar(Libro libro)
	{
		repo.save(libro);
	}
	
	public Libro obtener(long id)
	{
		return repo.findById(id).get();
	}
	
	public void borrar(long id)
	{
		repo.deleteById(id);
	}
}
