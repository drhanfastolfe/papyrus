package com.papyrus.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService
{
    @Autowired
    CategoriaRepository repo;
    
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
}
