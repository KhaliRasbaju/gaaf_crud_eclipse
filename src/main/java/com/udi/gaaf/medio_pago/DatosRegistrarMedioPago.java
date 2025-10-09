package com.udi.gaaf.medio_pago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarMedioPago(
	@Positive
	@NotNull
	Long idMetodoPago,
	@NotBlank
	String referencia
) {

}
