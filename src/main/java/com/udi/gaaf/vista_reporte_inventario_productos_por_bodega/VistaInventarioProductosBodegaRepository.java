package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaInventarioProductosBodega}.
 * 
 * <p>Proporciona operaciones CRUD y consultas personalizadas sobre la vista
 * de inventario de productos por bodega.</p>
 */
@Repository
public interface VistaInventarioProductosBodegaRepository extends JpaRepository<VistaInventarioProductosBodega, String> {

}
