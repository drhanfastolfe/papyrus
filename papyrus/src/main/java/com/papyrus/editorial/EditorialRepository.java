package com.papyrus.editorial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long>
{
    @Query("select e from Editorial e where e.nombre = ?1")
    Editorial findByNombre(String nombre);

    @Query("select e from Editorial e where unaccent(lower(e.nombre)) like %?1%")
    public List<Editorial> search(String keyword);
}
