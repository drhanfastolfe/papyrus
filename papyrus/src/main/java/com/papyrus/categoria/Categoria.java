package com.papyrus.categoria;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.papyrus.libro.Libro;

@Entity
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private List<Libro> libros;
    
    public Categoria()
    {
        
    }

    public List<Libro> getLibros()
    {
        return libros;
    }

    public void setLibros(List<Libro> libros)
    {
        this.libros = libros;
    }

    public Categoria(String nombre)
    {
        this.nombre = nombre;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
