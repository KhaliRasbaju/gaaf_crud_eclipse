package com.udi.gaaf.ubicacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona las operaciones CRUD sobre la entidad {@link Ubicacion}.
 * Permite realizar consultas personalizadas basadas en el proveedor.
 */
@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

	/**
	 * Busca todas las ubicaciones asociadas a un proveedor específico por su NIT.
	 *
	 * @param id Identificador del proveedor (NIT).
	 * @return Lista de ubicaciones asociadas al proveedor.
	 */
	List<Ubicacion> findAllByProveedor_Nit(Long id);
}
