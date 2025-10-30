package com.udi.gaaf.proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Proveedor}.
 * Permite realizar operaciones CRUD sobre la tabla de proveedores.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {}
