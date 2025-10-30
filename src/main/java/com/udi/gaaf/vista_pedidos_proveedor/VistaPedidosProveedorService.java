package com.udi.gaaf.vista_pedidos_proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<VistaPedidosProveedor> obtenerReporte() {
		return repository.findAll();
	}
}
