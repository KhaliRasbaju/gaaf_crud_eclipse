package com.udi.gaaf.vista_pedidos_proveedor;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio encargado de la lógica de negocio relacionada con la vista de pedidos de proveedores.
 * 
 * <p>Proporciona métodos para obtener información consolidada sobre los pedidos,
 * útil para reportes y paneles administrativos.</p>
 */
@Service
public class VistaPedidosProveedorService {

	@Autowired
	private VistaPedidosProveedorRepository repository;

	/**
	 * Obtiene el reporte completo de los pedidos realizados a proveedores.
	 * 
	 * @return Lista con todos los registros de la vista {@link VistaPedidosProveedor}.
	 */
	@Transactional(readOnly = true)
	public Page<VistaPedidosProveedor> obtenerReporte(
			LocalDate fechaPedido,
			LocalDate fechaEntrega,
			String estado,
			String metodoPago,
			Double valorPedido,
			Pageable paginacion) {
		return repository.findAllWithFilters(
				fechaPedido,
				fechaEntrega,
				estado,
				metodoPago,
				valorPedido,
				paginacion);
	}
}
