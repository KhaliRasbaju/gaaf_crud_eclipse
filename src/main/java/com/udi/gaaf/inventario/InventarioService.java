package com.udi.gaaf.inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.vista_reporte_bodega.DatosBodegaDto;
import com.udi.gaaf.vista_reporte_bodega.VistaReporteBodegaService;
import com.udi.gaaf.vista_reporte_inventario.DatosInventarioDto;
import com.udi.gaaf.vista_reporte_inventario.VistaReporteInventarioService;

@Service
public class InventarioService {

	@Autowired
	private InventarioRepository repository;
	@Autowired
	private VistaReporteBodegaService reporteBodegaService;
	
	@Autowired 
	private VistaReporteInventarioService reporteInventarioService;
	
	
	private Integer getCantidadTotal(Integer cantidad_disponible_total, Integer cantidad_reservada_total) {
		return cantidad_disponible_total + cantidad_reservada_total;
	}
	
	public DatosReporte getReporte() {
		
		List<DatosBodegaDto> reporteBodega = reporteBodegaService.getReporteBodega();
		
		
		List<DatosInventarioDto> repoprteInventarios = reporteInventarioService.getReporteInventario();
		
		Integer cantidadTotal = reporteBodega.stream()
				.map(rb -> getCantidadTotal(rb.cantidadDisponibleTotal(), rb.cantidadReservadaTotal()))
				.reduce(0, Integer::sum);
		
		return new DatosReporte(repoprteInventarios, reporteBodega, cantidadTotal);
	}
	
}
