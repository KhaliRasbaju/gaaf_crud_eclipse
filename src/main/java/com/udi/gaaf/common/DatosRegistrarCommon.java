package com.udi.gaaf.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarCommon(
	@NotBlank
	@NotNull
	String nombre
) {

}
