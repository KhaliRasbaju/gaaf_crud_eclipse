package com.udi.gaaf.vista_pedidos_proveedor;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaPedidosProveedor}.
 * 
 * <p>Permite ejecutar operaciones de lectura sobre la vista de pedidos de proveedores.</p>
 */
@Repository
public interface VistaPedidosProveedorRepository extends JpaRepository<VistaPedidosProveedor, Long> {

	@Query("""
			SELECT v FROM VistaPedidosProveedor v
			WHERE	
				(:fechaPedido IS NULL OR v.fechaPedido = :fechaPedido)
				AND (:fechaEntrega IS NULL OR v.fechaEntrega = :fechaEntrega)
		        AND (
		            :estado IS NULL OR :estado = '' 
		            OR LOWER(v.estado) LIKE LOWER(CONCAT('%', :estado, '%'))
		        )
				AND (
		            :metodoPago IS NULL OR :metodoPago = '' 
		            OR LOWER(v.metodoPago) LIKE LOWER(CONCAT('%', :metodoPago, '%'))
		        )
		        AND (:valorPedido IS NULL OR v.valorPedido >= :valorPedido)
			""")
	Page<VistaPedidosProveedor> findAllWithFilters(
			@Param("fechaPedido") LocalDate fechaPedido,
			@Param("fechaEntrega") LocalDate fechaEntrega,
			@Param("estado") String estado,
			@Param("metodoPago") String metodoPago,
			@Param("valorPedido") Double valorPedido,
			Pageable paginacion
			);
}
