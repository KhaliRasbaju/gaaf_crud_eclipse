package com.udi.gaaf.vista_pedidos_proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VistaPedidosProveedorRepository extends JpaRepository<VistaPedidosProveedor, Long> {

}
