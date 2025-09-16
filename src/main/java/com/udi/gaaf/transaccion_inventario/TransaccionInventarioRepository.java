package com.udi.gaaf.transaccion_inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionInventarioRepository extends JpaRepository<TransaccionInventario, Long> {

}
