package com.papyrus.ejemplar;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.papyrus.editorial.Editorial;
import com.papyrus.editorial.EditorialService;
import com.papyrus.libro.Libro;
import com.papyrus.libro.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjemplarService
{
    @Autowired
    private EjemplarRepository repo;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private LibroService libroService;

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

    public List<Ejemplar> search(String keyword)
    {
        List<Ejemplar> listaEjemplares = new ArrayList<>();
        Set<Ejemplar> setEjemplares = new LinkedHashSet<>();

        setEjemplares.addAll(repo.search(keyword));

        for (Editorial editorial : editorialService.search(keyword))
        {
            setEjemplares.addAll(editorial.getListaEjemplares());    
        }

        for (Libro libro : libroService.search(keyword))
        {
            setEjemplares.addAll(libro.getListaEjemplares());    
        }

        List<Ejemplar> listaEjemplaresSet = new ArrayList<>(setEjemplares);
        listaEjemplares = listaEjemplaresSet;

        return listaEjemplares;
    }
}
