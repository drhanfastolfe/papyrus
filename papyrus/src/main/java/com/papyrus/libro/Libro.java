package com.papyrus.libro;

import javax.persistence.*;

@Entity
@Table
public class Libro
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String autor;
	private int anio;
	private String categoria;

	public Libro()
	{
	}

	public Libro(String titulo, String autor, short anio, String categoria)
	{
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.categoria = categoria;
	}

	public Libro(Long id, String titulo, String autor, short anio, String categoria)
	{
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.categoria = categoria;
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

	public String getAutor()
	{
		return autor;
	}

	public void setAutor(String autor)
	{
		this.autor = autor;
	}

	public int getAnio()
	{
		return anio;
	}

	public void setAnio(short anio)
	{
		this.anio = anio;
	}

	public String getCategoria()
	{
		return categoria;
	}

	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}

	@Override
	public String toString()
	{
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + ", categoria="
				+ categoria + "]";
	}
}
