package com.papyrus.libro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.papyrus.autor.Autor;
import com.papyrus.categoria.Categoria;
import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.libro_editorial.LibroEditorial;
import com.papyrus.seccion.Seccion;

@Entity
public class Libro 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private int anio_pub;
	private int seccion_id;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Ejemplar> ejemplares;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<LibroEditorial> libroEditoriales;

	@ManyToOne
	@JoinColumn(name = "seccion_id")
	private Seccion seccion;

	@ManyToMany
	@JoinTable
	(
		name = "libro_autor",
		joinColumns = @JoinColumn(name = "libro_id"),
		inverseJoinColumns = @JoinColumn(name = "autor_id")
	)
	private List<Autor> autores;

	@ManyToMany
	@JoinTable
	(
		name = "libro_categoria",
		joinColumns = @JoinColumn(name = "libro_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias;

	public Libro()
	{
	}

	public Libro(String titulo, int anio_pub)
	{
		this.titulo = titulo;
		this.anio_pub = anio_pub;
	}

	public List<LibroEditorial> getLibroEditoriales()
	{
		return libroEditoriales;
	}

	public void setLibroEditoriales(List<LibroEditorial> libroEditoriales)
	{
		this.libroEditoriales = libroEditoriales;
	}

	public int getSeccion_id()
	{
		return seccion_id;
	}

	public void setSeccion_id(int seccion_id)
	{
		this.seccion_id = seccion_id;
	}

	public Seccion getSeccion()
	{
		return seccion;
	}

	public void setSeccion(Seccion seccion)
	{
		this.seccion = seccion;
	}

	public List<Autor> getAutores()
	{
		return autores;
	}

	public void setAutores(List<Autor> autores)
	{
		this.autores = autores;
	}

	public List<Categoria> getCategorias()
	{
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias)
	{
		this.categorias = categorias;
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
