package com.papyrus.socio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.papyrus.prestamo.Prestamo;

@Entity
public class Socio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private LocalDate fecha_nac;

    @OneToMany(mappedBy = "socio")
    private List<Prestamo> listaPrestamos;

    public Socio()
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

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDate getFecha_nac()
    {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac)
    {
        this.fecha_nac = fecha_nac;
    }

    public List<Prestamo> getListaPrestamos()
    {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos)
    {
        this.listaPrestamos = listaPrestamos;
    }
    
    
}
