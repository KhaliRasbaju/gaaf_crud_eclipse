package com.udi.gaaf.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarProducto(
	@NotBlank
	String nombre,
	@NotBlank
	String descripcion,
	@NotNull(message = "este campo es requerido de tipo [CACAO | INGREDIENTES_COMPLEMENTARIOS]")
	TipoProducto tipo
) {

}
