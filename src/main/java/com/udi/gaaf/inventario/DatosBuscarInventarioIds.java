package com.udi.gaaf.inventario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosBuscarInventarioIds(
	@Positive
	@NotNull
	Long idBodega,
	@Positive
	@NotNull
	Long idProducto
) {

}
