package com.papyrus.libro_editorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroEditorialService
{
    @Autowired
    private LibroEditorialRepository repo;

    public List<LibroEditorial> findByLibro_id(Long libro_id)
    {
        List<LibroEditorial> libroEditoriales = repo.findByLibro_id(libro_id);
        
        return libroEditoriales;
    }

    public List<LibroEditorial> findByEditorial_id(Long editorial_id)
    {
        List<LibroEditorial> libroEditoriales = repo.findByEditorial_id(editorial_id);
        
        return libroEditoriales;
    }
}
