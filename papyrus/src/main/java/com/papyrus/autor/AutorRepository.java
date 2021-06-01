package com.papyrus.autor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>
{
    @Query("select a from Autor a where unaccent(lower(a.nombre)) like %?1%")
    public List<Autor> search(String keyword);
}
