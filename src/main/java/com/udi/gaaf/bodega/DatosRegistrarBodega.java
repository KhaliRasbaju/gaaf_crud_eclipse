package com.udi.gaaf.bodega;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarBodega(
	@NotNull
	@NotBlank
	String nombre,
	@NotNull
	@NotBlank
	String ubicacion
) {

}
