package com.udi.gaaf.vista_reporte_compras;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la lógica de negocio relacionada con la vista de reportes de compras.
 * 
 * <p>Permite obtener información consolidada sobre los pedidos de compra
 * para su uso en reportes o paneles de administración.</p>
 */
@Service
public class VistaCompraService {

	@Autowired
	private VistaCompraRepository repository;

	/**
	 * Obtiene el reporte completo de todas las compras registradas.
	 * 
	 * @return Lista con todos los registros de la vista {@link VistaCompra}.
	 */
	public Page<VistaCompra> obtenerReporte(
			LocalDateTime fechaPedido,
			LocalDateTime fechaEntrega,
			String estado,
			String producto,
			Integer cantidad,
			Double valorPedido,
			Pageable paginacion) {
		return repository.findAllWithFilters(
				fechaPedido,
				fechaEntrega,
				estado,
				producto,
				cantidad,
				valorPedido,
				paginacion);
	}
}
