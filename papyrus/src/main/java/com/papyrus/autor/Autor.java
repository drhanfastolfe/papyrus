package com.papyrus.autor;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.papyrus.libro.Libro;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Autor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_nac;

    @ManyToMany(mappedBy = "listaAutores")
    private List<Libro> listaLibros;

    public Autor()
    {
        
    }

    public List<Libro> getListaLibros()
    {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros)
    {
        this.listaLibros = listaLibros;
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

    public LocalDate getFecha_nac()
    {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac)
    {
        this.fecha_nac = fecha_nac;
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
