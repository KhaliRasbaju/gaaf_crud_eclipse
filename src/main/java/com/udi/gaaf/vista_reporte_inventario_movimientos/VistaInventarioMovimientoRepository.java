package com.udi.gaaf.vista_reporte_inventario_movimientos;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaInventarioMovimiento}.
 * 
 * <p>Proporciona operaciones CRUD y consultas personalizadas sobre la vista 
 * de movimientos de inventario registrados en el sistema.</p>
 */
@Repository
public interface VistaInventarioMovimientoRepository extends JpaRepository<VistaInventarioMovimiento, Long> {

	
	
	@Query("""
		    SELECT v FROM VistaInventarioMovimiento v
		    WHERE 
		        (:fecha IS NULL OR v.fecha = :fecha)
		        AND (
		            :producto IS NULL OR :producto = '' 
		            OR LOWER(v.producto) LIKE LOWER(CONCAT('%', :producto, '%'))
		        )
		        AND (
		            :tipo IS NULL OR :tipo = '' 
		            OR LOWER(v.tipo) LIKE LOWER(CONCAT('%', :tipo, '%'))
		        )
		        AND (:cantidad IS NULL OR v.cantidad >= :cantidad)
		""")
		Page<VistaInventarioMovimiento> findAllWithFilters(
		    @Param("producto") String producto,
		    @Param("tipo") String tipo,
		    @Param("cantidad") Integer cantidad,
		    @Param("fecha") LocalDate fecha,
		    Pageable paginacion
		);

}

