package com.udi.gaaf.vista_reporte_bodega;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistaReporteBodegaService {

	@Autowired
	private VistaReporteBodegaRepository repository;
	
	public List<DatosBodegaDto> getReporteBodega() {
		var reporteBodega = repository.findAll();
		return reporteBodega.stream()
				.map(rb -> new DatosBodegaDto(rb.bodega, rb.cantidadDisponibleTotal, rb.cantidadReservadaTotal, rb.total ))
				.toList();
	}
	
}
