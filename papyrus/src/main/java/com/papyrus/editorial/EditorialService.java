package com.papyrus.editorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService
{
    @Autowired
    EditorialRepository repo;

    public Editorial findByNombre(String nombre)
    {   
        return repo.findByNombre(nombre);
    }

    public void save(Editorial editorial)
    {
        repo.save(editorial);
    }

    public void deleteById(Long id)
    {
        repo.deleteById(id);
    }
}
