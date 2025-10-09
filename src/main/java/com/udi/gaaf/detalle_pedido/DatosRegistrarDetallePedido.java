package com.udi.gaaf.detalle_pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarDetallePedido(
	@Positive
	@NotNull
	Float humedad,
	@Positive
	@NotNull
	Float estadoCacao,
	@Positive
	@NotNull
	Float fermentacion,
	@Positive
	@NotNull
	Integer cantidad,
	@Positive
	@NotNull
	Float peso,
	@Null
	String observacion,
	@Positive
	@NotNull
	Long idProducto
) {

}
