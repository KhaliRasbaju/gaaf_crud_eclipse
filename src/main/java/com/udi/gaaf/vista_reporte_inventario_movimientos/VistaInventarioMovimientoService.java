package com.udi.gaaf.vista_reporte_inventario_movimientos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistaInventarioMovimientoService {

	@Autowired
	private VistaInventarioMovimientoRepository repository;
	
	
	public List<VistaInventarioMovimiento> obtenerReporte() {
		return repository.findAll();
	}
}
