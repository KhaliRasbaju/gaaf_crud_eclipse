package com.udi.gaaf.vista_reporte_compras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VistaCompraRepository extends JpaRepository<VistaCompra, Long>{

}
