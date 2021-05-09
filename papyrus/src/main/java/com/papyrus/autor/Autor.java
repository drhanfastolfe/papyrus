package com.papyrus.autor;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.papyrus.libro.Libro;

@Entity
public class Autor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Date fecha_nac;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor()
    {
        
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

    public Date getFecha_nac()
    {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac)
    {
        this.fecha_nac = fecha_nac;
    }
}
