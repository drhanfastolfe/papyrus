package com.papyrus.ejemplar;

import java.util.List;

import com.papyrus.libro.Libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>
{
    List<Ejemplar> findByLibro(Libro libro);
}
