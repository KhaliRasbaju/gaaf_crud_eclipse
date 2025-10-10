package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VistaInventarioProductosBodegaRepository extends JpaRepository<VistaInventarioProductosBodega, String> {

}
