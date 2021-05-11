package com.papyrus.ejemplar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.papyrus.detalle.Detalle;

@Entity
public class Ejemplar
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private int detalle_id;

    @ManyToOne
    @JoinColumn(name = "detalle_id", insertable = false, updatable = false)
    private Detalle detalle;
    
    public Ejemplar()
    {
        
    }

    public int getDetalle_id()
    {
        return detalle_id;
    }

    public void setDetalle_id(int detalle_id)
    {
        this.detalle_id = detalle_id;
    }

    public Detalle getDetalle()
    {
        return detalle;
    }

    public void setDetalle(Detalle detalle)
    {
        this.detalle = detalle;
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
