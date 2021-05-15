package com.papyrus.ejemplar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.papyrus.editorial.Editorial;
import com.papyrus.libro.Libro;
import com.papyrus.prestamo.Prestamo;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Ejemplar
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long libro_id;
    private Long editorial_id;
    private int edicion;
    private String isbn;
    private int paginas;
    private String estado;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_imp;

    //? @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha_ins = LocalDateTime.now();

    @OneToMany(mappedBy = "ejemplar")
    private List<Prestamo> prestamo;

    @ManyToOne
    @JoinColumn(name = "libro_id", insertable = false, updatable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "editorial_id", insertable = false, updatable = false)
    private Editorial editorial;
    
    public Ejemplar()
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

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public LocalDate getFecha_imp()
    {
        return fecha_imp;
    }

    public void setFecha_imp(LocalDate fecha_imp)
    {
        this.fecha_imp = fecha_imp;
    }

    public LocalDateTime getFecha_ins()
    {
        return fecha_ins;
    }

    public void setFecha_ins(LocalDateTime fecha_ins)
    {
        this.fecha_ins = fecha_ins;
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
