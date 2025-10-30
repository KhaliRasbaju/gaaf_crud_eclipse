package com.udi.gaaf.vista_reporte_compras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaCompra}.
 * 
 * <p>Proporciona operaciones CRUD y consultas predefinidas
 * sobre la vista de reportes de compras.</p>
 */
@Repository
public interface VistaCompraRepository extends JpaRepository<VistaCompra, Long> {

}
