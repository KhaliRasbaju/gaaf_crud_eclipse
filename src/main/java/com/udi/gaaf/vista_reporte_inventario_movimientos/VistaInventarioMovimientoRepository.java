package com.udi.gaaf.vista_reporte_inventario_movimientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VistaInventarioMovimientoRepository extends JpaRepository<VistaInventarioMovimiento, String> {

}
