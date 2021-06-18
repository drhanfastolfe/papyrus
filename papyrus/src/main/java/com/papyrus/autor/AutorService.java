package com.papyrus.autor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService
{
    @Autowired
    private AutorRepository repo;
    
    public List<Autor> findAll()
    {
        return repo.findAll();
    }

    public Autor findById(Long id)
    {
        return repo.findById(id).get();    
    }

    public void save(Autor autor)
    {
        repo.save(autor);
    }

    public void deleteById(Long id)
    {
        repo.deleteById(id);
    }

    public List<Autor> search(String keyword)
    {
        return repo.search(keyword);
    }

    public List<Autor> autoresMasLeidos()
    {
        List<Autor> autoresMasLeidos = repo.findAll();
        
        Comparator<Autor> comparaPrestamos = (Autor a1, Autor a2) -> Integer.valueOf(a1.prestamosCount()).compareTo(Integer.valueOf(a2.prestamosCount())); 

		Collections.sort(autoresMasLeidos, comparaPrestamos.reversed());

		if(autoresMasLeidos.size() > 5)
		{
			autoresMasLeidos = autoresMasLeidos.subList(0, 5);
		}

        return autoresMasLeidos;
    }
}
