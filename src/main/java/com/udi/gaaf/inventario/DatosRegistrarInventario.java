package com.udi.gaaf.inventario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarInventario(
	@NotNull
	LocalDateTime fecha,
	@NotNull
	@Positive
	Integer cantidad,
	@NotNull
	@Positive
	Long idProducto,
	@NotNull
	@Positive
	Long idBodega
) {

}
