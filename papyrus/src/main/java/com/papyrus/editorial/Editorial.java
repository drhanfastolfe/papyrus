package com.papyrus.editorial;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import com.papyrus.libro_editorial.LibroEditorial;

import org.springframework.data.annotation.Id;

@Entity
public class Editorial
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nombre;

    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL)
    private List<LibroEditorial> libroEditoriales;

    public Editorial(Long nombre, List<LibroEditorial> libroEditoriales)
    {
        this.nombre = nombre;
        this.libroEditoriales = libroEditoriales;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getNombre()
    {
        return nombre;
    }

    public void setNombre(Long nombre)
    {
        this.nombre = nombre;
    }

    public List<LibroEditorial> getLibroEditoriales()
    {
        return libroEditoriales;
    }

    public void setLibroEditoriales(List<LibroEditorial> libroEditoriales)
    {
        this.libroEditoriales = libroEditoriales;
    }
}
