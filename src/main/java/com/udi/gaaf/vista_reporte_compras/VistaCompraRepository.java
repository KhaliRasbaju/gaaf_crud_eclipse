package com.udi.gaaf.vista_reporte_compras;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaCompra}.
 * 
 * <p>Proporciona operaciones CRUD y consultas predefinidas
 * sobre la vista de reportes de compras.</p>
 */
@Repository
public interface VistaCompraRepository extends JpaRepository<VistaCompra, Long> {
	
	@Query("""
			
			SELECT v FROM VistaCompra v
			WHERE
				(:fechaPedido IS NULL OR v.fechaPedido = :fechaPedido)
				AND (:fechaEntrega IS NULL OR v.fechaEntrega = :fechaEntrega)
		        AND (
		            :estado IS NULL OR :estado = '' 
		            OR LOWER(v.estado) LIKE LOWER(CONCAT('%', :estado, '%'))
		        )
				AND (
		            :producto IS NULL OR :producto = '' 
		            OR LOWER(v.producto) LIKE LOWER(CONCAT('%', :producto, '%'))
		        )
		        AND (:cantidad IS NULL OR v.cantidad >= :cantidad)
		        AND (:valorPedido IS NULL OR v.valorPedido >= :valorPedido)
			""")
	Page<VistaCompra> findAllWithFilters(
			@Param("fechaPedido") LocalDate fechaPedido,
			@Param("fechaEntrega") LocalDate fechaEntrega,
			@Param("estado") String estado,
			@Param("producto") String producto,
			@Param("cantidad") Integer cantidad,
			@Param("valorPedido") Double valorPedido,
			Pageable paginacion
			);
	
}
