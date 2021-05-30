package com.papyrus.prestamo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.empleado.Empleado;
import com.papyrus.socio.Socio;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Prestamo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ejemplar_id;
    private Long socio_id;
    private Long empleado_id;

    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha_inicio = LocalDateTime.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_fin;
    private LocalDateTime fecha_fin_real;

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

    public LocalDateTime getFecha_inicio()
    {
        return fecha_inicio;
    }

    public String getFecha_inicioFormateada()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        return this.getFecha_inicio().format(formatter);
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio)
    {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin()
    {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin)
    {
        this.fecha_fin = fecha_fin;
    }

    public LocalDateTime getFecha_fin_real()
    {
        return fecha_fin_real;
    }

    public String getFecha_fin_realFormateada()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fecha;

        if(this.getFecha_fin_real() != null)
        {
            fecha = this.getFecha_fin_real().format(formatter);
        }
        else
        {
            fecha = " ";
        }
        
        return fecha;
    }

    public void setFecha_fin_real(LocalDateTime fecha_fin_real)
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

    public boolean activo()
    {
        return this.fecha_fin_real == null;
    }
}
