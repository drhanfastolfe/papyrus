package com.papyrus.empleado;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.papyrus.prestamo.Prestamo;
import com.papyrus.rol.Rol;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Empleado
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_nac;
    private String usuario;
    private String contrasenia;

    @OneToMany(mappedBy = "empleado")
    private List<Prestamo> listaPrestamos;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	(
		name = "empleado_rol",
		joinColumns = @JoinColumn(name = "empleado_id"),
		inverseJoinColumns = @JoinColumn(name = "rol_id")
	)
	private Set<Rol> setRoles = new HashSet<>();

    public Empleado()
    {

    }

    public Set<Rol> getSetRoles()
    {
        return this.setRoles;
    }

    public void setSetRoles(Set<Rol> setRoles)
    {
        this.setRoles = setRoles;
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

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getContrasenia()
    {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public List<Prestamo> getListaPrestamos()
    {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos)
    {
        this.listaPrestamos = listaPrestamos;
    }

    public String getNombreCompleto()
    {
        String nombreCompleto = this.apellidos + ", " + this.nombre;

        return nombreCompleto;
    }

    public int prestamosCount()
    {
        return this.getListaPrestamos().size();
    }
}
