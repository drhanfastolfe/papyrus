package com.papyrus.libro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.papyrus.ejemplar.Ejemplar;

@Entity
public class Libro 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private int anio_pub;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Ejemplar> ejemplares;

	public Libro()
	{
	}

	public Libro(String titulo, int anio_pub)
	{
		this.titulo = titulo;
		this.anio_pub = anio_pub;
	}

	public List<Ejemplar> getEjemplares()
	{
		if (this.ejemplares == null) 
		{
			this.ejemplares = new ArrayList<>();
		}
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares)
	{
		this.ejemplares = ejemplares;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public int getAnio_pub()
	{
		return anio_pub;
	}

	public void setAnio_pub(int anio_pub)
	{
		this.anio_pub = anio_pub;
	}
}
