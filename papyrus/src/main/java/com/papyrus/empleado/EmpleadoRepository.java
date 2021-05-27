package com.papyrus.empleado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>
{
    public Empleado findByUsuario(String usuario);

    @Query("select e from Empleado e where unaccent(lower(concat(e.nombre, ' ', e.apellidos, ' ', e.dni, ' ', e.telefono, ' ', e.email, ' ', e.usuario))) like %?1%")
    public List<Empleado> search(String keyword);
}
