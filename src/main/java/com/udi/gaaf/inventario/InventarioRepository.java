package com.udi.gaaf.inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona las operaciones de persistencia
 * para la entidad {@link Inventario}.
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, InventarioId> {

    /**
     * Busca los inventarios asociados a una bodega.
     *
     * @param idBodega identificador de la bodega.
     * @return lista de inventarios relacionados con esa bodega.
     */
    List<Inventario> findByBodegaId(Long idBodega);

    /**
     * Busca un inventario específico por su bodega y producto.
     *
     * @param idBodega identificador de la bodega.
     * @param idProducto identificador del producto.
     * @return un {@link Optional} con el inventario encontrado o vacío si no existe.
     */
    Optional<Inventario> findById_IdBodegaAndId_IdProducto(Long idBodega, Long idProducto);
}
