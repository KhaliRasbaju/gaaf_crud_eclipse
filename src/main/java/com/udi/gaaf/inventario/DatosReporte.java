package com.udi.gaaf.inventario;

import java.util.List;

import com.udi.gaaf.vista_reporte_bodega.DatosBodegaDto;
import com.udi.gaaf.vista_reporte_inventario.DatosInventarioDto;



public record DatosReporte(
		
		List<DatosInventarioDto> reporteInventario,
		List<DatosBodegaDto> reporteBodega,
		Integer cantidad_total
		) {

}
