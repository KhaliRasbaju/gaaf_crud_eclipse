package com.udi.gaaf.entidad_bancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link EntidadBancaria}.
 */
@Repository
public interface EntidadBancariaRepository extends JpaRepository<EntidadBancaria, Long> {
}
