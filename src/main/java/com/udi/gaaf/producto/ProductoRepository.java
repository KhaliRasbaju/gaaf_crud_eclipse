package com.udi.gaaf.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Producto}.
 * Permite realizar operaciones CRUD sobre la tabla de productos.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {}
