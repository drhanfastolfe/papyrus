package com.papyrus.detalle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleService
{
    @Autowired
    DetalleRepository repo;
    
    public List<Detalle> findAll()
    {
        return repo.findAll();
    }

    public Detalle findById(Long id)
    {
        return repo.findById(id).get();    
    }

    public void save(Detalle detalle)
    {
        repo.save(detalle);
    }

    public void deleteById(Long id)
    {
        repo.deleteById(id);
    }
}
