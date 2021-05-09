package com.papyrus.libro_editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroEditorialRepository extends JpaRepository<LibroEditorial, Long>
{
    
}
