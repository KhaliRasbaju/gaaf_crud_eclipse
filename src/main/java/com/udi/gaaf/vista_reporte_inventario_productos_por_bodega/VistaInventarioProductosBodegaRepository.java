package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaInventarioProductosBodega}.
 * 
 * <p>Proporciona operaciones CRUD y consultas personalizadas sobre la vista
 * de inventario de productos por bodega.</p>
 */
@Repository
public interface VistaInventarioProductosBodegaRepository extends JpaRepository<VistaInventarioProductosBodega, Long> {

	@Query("""
			SELECT v FROM VistaInventarioProductosBodega v
			WHERE 
				(:fecha IS NULL OR v.fecha = :fecha)
				AND (
					:producto IS NULL OR :producto = '' 
					OR LOWER(v.producto) LIKE LOWER(CONCAT('%', :producto, '%'))
				)
				AND (
					:cantidad IS NULL OR v.cantidad = :cantidad
				)
			""")
	Page<VistaInventarioProductosBodega> findAllWithFilters(
			@Param("producto") String producto, 
			@Param("fecha") LocalDate fecha,
			@Param("cantidad") Integer cantidad, 
			Pageable paginacion);

}
