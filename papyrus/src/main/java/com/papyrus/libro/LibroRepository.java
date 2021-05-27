package com.papyrus.libro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>
{
    @Query("select l from Libro l where unaccent(lower(concat(l.titulo, ' ', (extract (year from l.fecha_pub))))) like %?1%")
	public List<Libro> search(String keyword);
}
