package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;


import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la lógica de negocio relacionada con el reporte
 * de inventario de productos por bodega.
 * 
 * <p>Permite consultar la vista que muestra la cantidad disponible de cada producto
 * en cada bodega del sistema.</p>
 */
@Service
public class VistaInventarioProductosBodegaService {

	@Autowired
	private VistaInventarioProductosBodegaRepository repository;

	/**
	 * Obtiene el reporte completo de inventario de productos agrupado por bodega.
	 * 
	 * @return Lista de objetos {@link VistaInventarioProductosBodega} que contienen
	 *         información sobre los productos y sus cantidades en cada bodega.
	 */
	public Page<VistaInventarioProductosBodega> obtenerReporte(
			String producto,
			LocalDate fecha,
			Integer cantidad, 
			Pageable paginacion
			) {
		return repository.findAll(paginacion);
		// return repository.findAllWithFilters(producto, fecha, cantidad, paginacion);
	}
}
