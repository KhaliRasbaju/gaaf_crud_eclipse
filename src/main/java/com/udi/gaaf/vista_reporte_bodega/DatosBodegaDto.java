package com.udi.gaaf.vista_reporte_bodega;

public record DatosBodegaDto(
	String bodega,
	Integer cantidadDisponibleTotal,
	Integer cantidadReservadaTotal,
	Integer total
) {

}
