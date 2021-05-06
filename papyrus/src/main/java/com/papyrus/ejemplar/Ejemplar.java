package com.papyrus.ejemplar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.papyrus.libro.Libro;

@Entity
public class Ejemplar
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private int libro_id;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    
    public Ejemplar(String estado)
    {
        this.estado = estado;
    }

    public int getLibro_id()
    {
        return libro_id;
    }

    public void setLibro_id(int libro_id)
    {
        this.libro_id = libro_id;
    }

    public Libro getLibro()
    {
        return libro;
    }

    public void setLibro(Libro libro)
    {
        this.libro = libro;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
}
