package com.papyrus.autor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService
{
    @Autowired
    AutorRepository repo;
    
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
}
