package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;
	
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
		return repo.search(keyword);
	}
}
