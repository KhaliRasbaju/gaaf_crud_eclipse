package com.udi.gaaf.inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, InventarioId> {

	List<Inventario> findByBodegaId(Long idBodega);
	
	Optional<Inventario> findById_IdBodegaAndId_IdProducto(Long idBodega, Long idProducto);
}
