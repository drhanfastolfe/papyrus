package com.papyrus.ejemplar;

import java.util.List;

import com.papyrus.libro.Libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjemplarService
{
    @Autowired
    private EjemplarRepository repo;

    public List<Ejemplar> ejemplaresLibro(Libro libro)
    {
        List<Ejemplar> ejemplares = repo.findByLibro(libro);
        
        return ejemplares;
    }
}
