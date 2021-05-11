package com.papyrus.ejemplar;

import java.util.List;

import com.papyrus.detalle.Detalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>
{
    List<Ejemplar> findByDetalle(Detalle detalle);
}
