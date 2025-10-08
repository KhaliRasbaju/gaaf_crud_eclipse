package com.udi.gaaf.proveedor;


import com.udi.gaaf.cuenta.DatosRegistrarCuenta;
import com.udi.gaaf.ubicacion.DatosRegistrarUbicacion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarProveedor(
	@Positive
	@NotNull
	Long nit,
	@NotBlank
	String nombre,
	@NotBlank
	String telefono,
	@Email
	@NotBlank
	String correo,
	@NotBlank
	String direccion,
	@NotNull
	@Valid
	DatosRegistrarUbicacion ubicacion,
	@NotNull
	@Valid
	DatosRegistrarCuenta cuenta
) {
	

}
