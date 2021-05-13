package com.papyrus.editorial;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.papyrus.ejemplar.Ejemplar;

@Entity
public class Editorial
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL)
    private List<Ejemplar> listaEjemplares;

    public Editorial()
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

    public List<Ejemplar> getListaEjemplares()
    {
        return listaEjemplares;
    }

    public void setListaEjemplares(List<Ejemplar> listaEjemplares)
    {
        this.listaEjemplares = listaEjemplares;
    }
}
