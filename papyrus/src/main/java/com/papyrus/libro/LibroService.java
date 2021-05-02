package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;
	
	public List<Libro> listAll()
	{
		return repo.findAll();
	}
	
	public void save(Libro libro)
	{
		repo.save(libro);
	}
	
	public Libro get(long id)
	{
		return repo.findById(id).get();
	}
	
	public void delete(long id)
	{
		repo.deleteById(id);
	}
}
