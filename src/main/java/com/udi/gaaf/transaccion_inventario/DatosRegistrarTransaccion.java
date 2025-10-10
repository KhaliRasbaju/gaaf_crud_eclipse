package com.udi.gaaf.transaccion_inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarTransaccion(
		
	@NotNull(message = "campo se requerid [ENTRADA | PRODUCCION | MERMA]")
	TipoTransaccion tipo,
	
	@NotNull
	@Positive
	Integer cantidad,
	
	@NotBlank
	String observacion,
	
	@Positive
	Long idPedido,
	
	@NotNull
	@Positive
	Long idProducto,
	
	@NotNull
	@Positive
	Long idBodega
) {

}
