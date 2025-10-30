package com.udi.gaaf.bodega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de bodegas.
 * <p>
 * Proporciona operaciones CRUD y consultas adicionales
 * sobre la entidad {@link Bodega}.
 * </p>
 */
@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {

}
