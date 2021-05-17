package com.papyrus.empleado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>
{
    public Optional<Empleado> findByUsuario(String usuario);
}
