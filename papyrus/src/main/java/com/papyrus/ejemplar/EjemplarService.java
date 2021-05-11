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

    public List<Ejemplar> ejemplaresDetalle(Detalle detalle)
    {
        List<Ejemplar> detalles = repo.findByDetalle(detalle);
        
        return detalles;
    }
}
