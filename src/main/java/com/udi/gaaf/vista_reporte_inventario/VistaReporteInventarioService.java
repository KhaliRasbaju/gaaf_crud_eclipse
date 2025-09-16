package com.udi.gaaf.vista_reporte_inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistaReporteInventarioService {

	
	@Autowired
	private VistaReporteInventarioRepository repository;
	
	public List<DatosInventarioDto> getReporteInventario(){
		var reporteInventario = repository.findAll();
		return reporteInventario.stream()	
				.map(ri -> new DatosInventarioDto(ri.fecha, ri.cantidadDisponible, ri.cantidadReservada, ri.producto, ri.bodega))
				.toList();
	}
	
}
