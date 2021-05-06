package com.papyrus.seccion;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.papyrus.libro.Libro;

@Entity
public class Seccion
{
    @Id
    private Integer id;
    private String nombre;

    @OneToMany(mappedBy = "seccion")
    private List<Libro> libros;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public List<Libro> getLibros()
    {
        return libros;
    }

    public void setLibros(List<Libro> libros)
    {
        this.libros = libros;
    }
}
