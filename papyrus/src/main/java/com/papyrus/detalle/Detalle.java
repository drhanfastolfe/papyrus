package com.papyrus.detalle;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.papyrus.editorial.Editorial;
import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.libro.Libro;

@Entity
public class Detalle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long libro_id;
    private Long editorial_id;
    private int edicion;
    private String isbn;
    private int paginas;
    private Date fecha_imp;

    @OneToMany(mappedBy = "detalle", cascade = CascadeType.ALL)
    private List<Ejemplar> ejemplares;

    @ManyToOne
    @JoinColumn(name = "libro_id", insertable = false, updatable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "editorial_id", insertable = false, updatable = false)
    private Editorial editorial;

    //? contructurs
    public Detalle()
    {
        
    }

    public List<Ejemplar> getEjemplares()
    {
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

    public Long getLibro_id()
    {
        return libro_id;
    }

    public void setLibro_id(Long libro_id)
    {
        this.libro_id = libro_id;
    }

    public Long getEditorial_id()
    {
        return editorial_id;
    }

    public void setEditorial_id(Long editorial_id)
    {
        this.editorial_id = editorial_id;
    }
    
    public int getEdicion()
    {
        return edicion;
    }

    public void setEdicion(int edicion)
    {
        this.edicion = edicion;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getPaginas()
    {
        return paginas;
    }

    public void setPaginas(int paginas)
    {
        this.paginas = paginas;
    }

    public Date getFecha_imp()
    {
        return fecha_imp;
    }

    public void setFecha_imp(Date fecha_imp)
    {
        this.fecha_imp = fecha_imp;
    }

    public Libro getLibro()
    {
        return libro;
    }

    public void setLibro(Libro libro)
    {
        this.libro = libro;
    }

    public Editorial getEditorial()
    {
        return editorial;
    }

    public void setEditorial(Editorial editorial)
    {
        this.editorial = editorial;
    }
}
