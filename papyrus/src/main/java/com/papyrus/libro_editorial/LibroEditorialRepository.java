package com.papyrus.libro_editorial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroEditorialRepository extends JpaRepository<LibroEditorial, Long>
{
    @Query("select el from LibroEditorial el where el.libro_id = ?1")
    List<LibroEditorial> findByLibro_id(Long libro_id);

    @Query("select el from LibroEditorial el where el.editorial_id = ?1")
    List<LibroEditorial> findByEditorial_id(Long editorial_id);
}
