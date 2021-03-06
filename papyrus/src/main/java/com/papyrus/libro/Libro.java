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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Libro 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha_pub;
	private Integer seccion_id;

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
	private List<Autor> listaAutores;

	@ManyToMany
	@JoinTable
	(
		name = "libro_categoria",
		joinColumns = @JoinColumn(name = "libro_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> listaCategorias;

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

	public Integer getSeccion_id()
	{
		return seccion_id;
	}

	public void setSeccion_id(Integer seccion_id)
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

	public List<Autor> getListaAutores()
	{
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores)
	{
		this.listaAutores = listaAutores;
	}

	public List<Categoria> getListaCategorias()
	{
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias)
	{
		this.listaCategorias = listaCategorias;
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

		for(int i = 0; i < this.getListaAutores().size(); i++)
		{
			autores += this.getListaAutores().get(i).getNombre();
			
			if(i != this.getListaAutores().size() - 1)
			{
				autores += ", ";
			}
		}
		
		return autores;
	}

	public String getCategoriasStr()
	{
		String categorias = "";

		for(int i = 0; i < this.getListaCategorias().size(); i++)
		{
			categorias += this.getListaCategorias().get(i).getNombre();
			
			if(i != this.getListaCategorias().size() - 1)
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

	public int getEjemplaresDisponiblesCount()
	{
		int count = 0;

		for(Ejemplar ejemplar : this.listaEjemplares)
		{
			if (ejemplar.disponible()) count++;
		}

		return count;
	}

	public int prestamosCount()
	{
		int prestamos = 0;

		for(Ejemplar ejemplar : this.getListaEjemplares())
		{
			prestamos += ejemplar.getListaPrestamos().size();
		}

		return prestamos;
	}
}