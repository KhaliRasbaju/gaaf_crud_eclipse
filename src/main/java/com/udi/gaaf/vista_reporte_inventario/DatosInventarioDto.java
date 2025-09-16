package com.udi.gaaf.vista_reporte_inventario;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;


public record DatosInventarioDto(
	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	LocalDate fecha,
	Integer cantidadDisponible,
	Integer cantidadReservada,
	String producto,
	String bodega
		
) {

}
