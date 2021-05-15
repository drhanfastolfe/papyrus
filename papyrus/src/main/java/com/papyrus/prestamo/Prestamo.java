package com.papyrus.prestamo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.empleado.Empleado;
import com.papyrus.socio.Socio;

@Entity
public class Prestamo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ejemplar_id;
    private Long socio_id;
    private Long empleado_id;
    private LocalDateTime fecha_indicio = LocalDateTime.now();
    private LocalDate fecha_fin;
    private LocalDate fecha_fin_real;

    @ManyToOne
    @JoinColumn(name = "ejemplar_id", insertable = false, updatable = false)
    private Ejemplar ejemplar;

    @ManyToOne
    @JoinColumn(name = "socio_id", insertable = false, updatable = false)
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "empleado_id", insertable = false, updatable = false)
    private Empleado empleado;

    public Prestamo()
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

    public Long getEjemplar_id()
    {
        return ejemplar_id;
    }

    public void setEjemplar_id(Long ejemplar_id)
    {
        this.ejemplar_id = ejemplar_id;
    }

    public Long getSocio_id()
    {
        return socio_id;
    }

    public void setSocio_id(Long socio_id)
    {
        this.socio_id = socio_id;
    }

    public Long getEmpleado_id()
    {
        return empleado_id;
    }

    public void setEmpleado_id(Long empleado_id)
    {
        this.empleado_id = empleado_id;
    }

    public LocalDateTime getFecha_indicio()
    {
        return fecha_indicio;
    }

    public void setFecha_indicio(LocalDateTime fecha_indicio)
    {
        this.fecha_indicio = fecha_indicio;
    }

    public LocalDate getFecha_fin()
    {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin)
    {
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getFecha_fin_real()
    {
        return fecha_fin_real;
    }

    public void setFecha_fin_real(LocalDate fecha_fin_real)
    {
        this.fecha_fin_real = fecha_fin_real;
    }

    public Ejemplar getEjemplar()
    {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar)
    {
        this.ejemplar = ejemplar;
    }

    public Socio getSocio()
    {
        return socio;
    }

    public void setSocio(Socio socio)
    {
        this.socio = socio;
    }

    public Empleado getEmpleado()
    {
        return empleado;
    }

    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }
}
