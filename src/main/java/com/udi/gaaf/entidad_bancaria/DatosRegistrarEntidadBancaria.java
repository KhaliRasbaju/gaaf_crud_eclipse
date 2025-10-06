package com.udi.gaaf.entidad_bancaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarEntidadBancaria(
	@NotBlank
	@NotNull
	String nombre
) {

}
