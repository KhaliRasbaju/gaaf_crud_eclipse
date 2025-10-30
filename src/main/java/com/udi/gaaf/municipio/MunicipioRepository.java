package com.udi.gaaf.municipio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Municipio}.
 * Permite realizar operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    /**
     * Busca todos los municipios pertenecientes a un departamento específico.
     *
     * @param id ID del departamento.
     * @return Lista de municipios pertenecientes a ese departamento.
     */
    List<Municipio> findAllByDepartamento_Id(Long id);
}
