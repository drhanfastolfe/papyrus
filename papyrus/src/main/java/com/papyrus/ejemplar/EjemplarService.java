package com.papyrus.ejemplar;

import java.util.List;

import com.papyrus.detalle.Detalle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjemplarService
{
    @Autowired
    private EjemplarRepository repo;

    public List<Ejemplar> findAll()
    {
        return repo.findAll();
    }
    
    public Ejemplar findById(Long id)
    {
        return repo.findById(id).get();    
    }

    public void save(Ejemplar ejemplar)
    {
        repo.save(ejemplar);
    }
    
    public void deleteById(Long id)
    {
        repo.deleteById(id);
    }

    public List<Ejemplar> findByDetalle(Detalle detalle)
    {
        List<Ejemplar> listaEjemplares = repo.findByDetalle(detalle);
        
        return listaEjemplares;
    }
}
