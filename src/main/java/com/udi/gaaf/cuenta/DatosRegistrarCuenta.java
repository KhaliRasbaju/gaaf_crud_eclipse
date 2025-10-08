package com.udi.gaaf.cuenta;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarCuenta(
	@Positive
	@NotNull
	String numero,
	@NotNull(message = "No puede ser nulo debe ser [AHORROS | CORRIENTE]")
	TipoCuenta tipo,
	
	@Positive
	@NotNull(message = "Numero de la cuenta bancaria requerido")
	Long idEntidad
) {

}
