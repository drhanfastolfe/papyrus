package com.papyrus.prestamo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>
{
    List<Prestamo> findBySocio_id(Long socio_id);
    // @Query("SELECT p FROM papyrus.prestamo p WHERE CONCAT(p.socio_id, ' ') LIKE %?1%")
    // public List<Prestamo> search(String keyword);
}
