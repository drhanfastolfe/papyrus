package com.papyrus.socio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>
{
    public Socio findByDni(String dni);

    @Query("select s from Socio s where unaccent(lower(concat(s.nombre, ' ', s.apellidos))) like %?1%")
    public List<Socio> searchByNombreApellidos(String nombreApellidos);

    @Query("select s from Socio s where unaccent(lower(concat(s.nombre, ' ', s.apellidos, ' ', s.dni, ' ', s.telefono, ' ', s.email))) like %?1%")
    public List<Socio> search(String keyword);
}
