package com.udi.gaaf.ubicacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarUbicacion(
	@NotBlank
	String direccion,
	@Positive
	@NotNull
	Long idMunicipio
) {

}
