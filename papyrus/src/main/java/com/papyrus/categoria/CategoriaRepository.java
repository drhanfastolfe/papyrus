package com.papyrus.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{
    @Query("select c from Categoria c where unaccent(lower(c.nombre)) like %?1%")
    public List<Categoria> search(String keyword);
}
