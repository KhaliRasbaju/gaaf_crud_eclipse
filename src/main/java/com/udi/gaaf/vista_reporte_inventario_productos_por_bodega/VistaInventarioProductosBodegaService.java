package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistaInventarioProductosBodegaService {

	@Autowired
	private VistaInventarioProductosBodegaRepository repository;
	
	public List<VistaInventarioProductosBodega>  obtenerReporte() {
		return repository.findAll();
	}
}
