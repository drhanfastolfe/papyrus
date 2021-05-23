package com.papyrus.rol;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.papyrus.empleado.Empleado;

@Entity
public class Rol
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String rol;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public List<Empleado> getListaEmpleado()
	{
		return listaEmpleado;
	}

	public void setListaEmpleado(List<Empleado> listaEmpleado)
	{
		this.listaEmpleado = listaEmpleado;
	}

	@ManyToMany(mappedBy = "setRoles")
    private List<Empleado> listaEmpleado;
	
	public String getRol()
    {
		return rol;
	}

	public void setRol(String rol)
    {
		this.rol = rol;
	}
}
