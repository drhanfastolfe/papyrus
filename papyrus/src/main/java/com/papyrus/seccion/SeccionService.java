package com.papyrus.seccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeccionService
{
    @Autowired
    private SeccionRepository seccionRepository;

    public List<Seccion> findAll()
    {
        return seccionRepository.findAll();
    }

    public Seccion findById(Integer id)
    {
        return seccionRepository.findById(id).get();    
    }

    public void save(Seccion seccion)
    {
        seccionRepository.save(seccion);
    }

    public void deleteById(Integer id)
    {
        seccionRepository.deleteById(id);
    }    
}
