package com.udi.gaaf.vista_pedidos_proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para acceder a los datos de la vista {@link VistaPedidosProveedor}.
 * 
 * <p>Permite ejecutar operaciones de lectura sobre la vista de pedidos de proveedores.</p>
 */
@Repository
public interface VistaPedidosProveedorRepository extends JpaRepository<VistaPedidosProveedor, Long> {

}
