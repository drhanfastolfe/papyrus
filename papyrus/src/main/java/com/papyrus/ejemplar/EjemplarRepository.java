package com.papyrus.ejemplar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>
{
    @Query("select e from Ejemplar e where unaccent(lower(concat(e.edicion, ' ', e.isbn, ' ', e.estado))) like %?1%")
    public List<Ejemplar> search(String keyword);
}
