package com.papyrus.editorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService
{
    @Autowired
    EditorialRepository repo;

    public List<Editorial> findAll()
    {
        return repo.findAll();
    }
    
    public Editorial findById(Long id)
    {
        return repo.findById(id).get();    
    }

    public void save(Editorial editorial)
    {
        repo.save(editorial);
    }
    
    public void deleteById(Long id)
    {
        repo.deleteById(id);
    }

    public Editorial findByNombre(String nombre)
    {   
        return repo.findByNombre(nombre);
    }
}
