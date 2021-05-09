package com.papyrus.editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long>
{
    @Query("select e from Editorial e where e.nombre = ?1")
    Editorial findByNombre(String nombre);
}
