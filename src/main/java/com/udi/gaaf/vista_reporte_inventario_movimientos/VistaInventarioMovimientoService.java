package com.udi.gaaf.vista_reporte_inventario_movimientos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la lógica de negocio relacionada con el reporte
 * de movimientos del inventario.
 * 
 * <p>Permite consultar todos los registros de movimientos almacenados en la vista
 * {@code vista_reporte_inventario_movimientos}, consolidando entradas y salidas
 * de productos en distintas bodegas.</p>
 */
@Service
public class VistaInventarioMovimientoService {

	@Autowired
	private VistaInventarioMovimientoRepository repository;

	/**
	 * Obtiene el reporte completo de todos los movimientos de inventario registrados.
	 * 
	 * @return Lista de objetos {@link VistaInventarioMovimiento} que representan los
	 *         movimientos del inventario.
	 */
	public List<VistaInventarioMovimiento> obtenerReporte() {
		return repository.findAll();
	}
}
