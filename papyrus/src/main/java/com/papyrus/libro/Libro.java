package com.papyrus.libro;

import java.time.LocalDate;
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
import com.papyrus.seccion.Seccion;

@Entity
public class Libro 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private LocalDate fecha_pub;
	private int seccion_id;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Ejemplar> listaEjemplares;

	@ManyToOne
	@JoinColumn(name = "seccion_id", insertable = false, updatable = false)
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

	public List<Ejemplar> getListaEjemplares()
	{
		return listaEjemplares;
	}

	public void setListaEjemplares(List<Ejemplar> listaEjemplares)
	{
		this.listaEjemplares = listaEjemplares;
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

	public LocalDate getFecha_pub()
	{
		return fecha_pub;
	}

	public void setFecha_pub(LocalDate fecha_pub)
	{
		this.fecha_pub = fecha_pub;
	}

	public String getEditorialesStr()
	{
		String editoriales = "";

		for(int i = 0; i < this.listaEjemplares.size(); i++)
		{
			if(!editoriales.contains(this.listaEjemplares.get(i).getEditorial().getNombre()))
			{
				editoriales += this.listaEjemplares.get(i).getEditorial().getNombre();
				
				if((i != this.listaEjemplares.size() - 1) && (!editoriales.contains(this.listaEjemplares.get(i + 1).getEditorial().getNombre())))
				{
					editoriales += ", ";
				}
			}
		}
		
		return editoriales;
	}

	public String getAutoresStr()
	{
		String autores = "";

		for(int i = 0; i < this.getAutores().size(); i++)
		{
			autores += this.getAutores().get(i).getNombre();
			
			if(i != this.getAutores().size() - 1)
			{
				autores += ", ";
			}
		}
		
		return autores;
	}

	public String getCategoriasStr()
	{
		String categorias = "";

		for(int i = 0; i < this.getCategorias().size(); i++)
		{
			categorias += this.getCategorias().get(i).getNombre();
			
			if(i != this.getCategorias().size() - 1)
			{
				categorias += ", ";
			}
		}
		
		return categorias;
	}

	public int getEjemplaresCount()
	{
		return this.getListaEjemplares().size();
	}
}