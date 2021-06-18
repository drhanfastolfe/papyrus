package com.papyrus.categoria;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService
{
    @Autowired
    private CategoriaRepository repo;
    
    public List<Categoria> findAll()
    {
        return repo.findAll();
    }

    public Categoria findById(Long id)
    {
        return repo.findById(id).get();    
    }

    public void save(Categoria categoria)
    {
        repo.save(categoria);
    }

    public void deleteById(Long id)
    {
        repo.deleteById(id);
    }
    
    public List<Categoria> search(String keyword)
    {
        return repo.search(keyword);
    }

    public List<Categoria> categoriasMasLeidos()
    {
        List<Categoria> categoriaesMasLeidos = repo.findAll();
        
        Comparator<Categoria> comparaPrestamos = (Categoria c1, Categoria c2) -> Integer.valueOf(c1.prestamosCount()).compareTo(Integer.valueOf(c2.prestamosCount())); 

		Collections.sort(categoriaesMasLeidos, comparaPrestamos.reversed());

		if(categoriaesMasLeidos.size() > 5)
		{
			categoriaesMasLeidos = categoriaesMasLeidos.subList(0, 5);
		}

        return categoriaesMasLeidos;
    }
}
