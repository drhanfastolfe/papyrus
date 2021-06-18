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

    @ManyToMany(mappedBy = "listaCategorias")
    private List<Libro> listaLibros;
    
    public Categoria()
    {
        
    }

    public List<Libro> getListaLibros()
    {
        return listaLibros;
    }

    public void setLisstaLibros(List<Libro> listaLibros)
    {
        this.listaLibros = listaLibros;
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

    public int prestamosCount()
    {
        int prestamos = 0;

        for (Libro libro : this.listaLibros)
        {
            prestamos += libro.prestamosCount();    
        }

        return prestamos;
    }
}
