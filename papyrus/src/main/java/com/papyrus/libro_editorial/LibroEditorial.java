package com.papyrus.libro_editorial;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.papyrus.editorial.Editorial;
import com.papyrus.libro.Libro;

@Entity
public class LibroEditorial
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long libro_id;
    private Long editorial_id;
    private String isbn;
    private int paginas;
    private Date fecha_imp;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    //? contructurs

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
