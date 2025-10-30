package com.udi.gaaf.vista_reporte_inventario_movimientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaInventarioMovimiento}.
 * 
 * <p>Proporciona operaciones CRUD y consultas personalizadas sobre la vista 
 * de movimientos de inventario registrados en el sistema.</p>
 */
@Repository
public interface VistaInventarioMovimientoRepository extends JpaRepository<VistaInventarioMovimiento, String> {

}
