package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.udi.gaaf.detalle_pedido.DatosRegistrarDetallePedido;
import com.udi.gaaf.medio_pago.DatosRegistrarMedioPago;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosRegistrarPedido(
	
	@NotNull()
	LocalDateTime fechaPedido,
	
	@Positive
	@NotNull
	Double valor,
	
	@Positive
	@NotNull
	Long nitProveedor,
	
	@NotNull
	@Valid
	DatosRegistrarMedioPago medioPago,
	
	@NotNull
	@Valid
	List<DatosRegistrarDetallePedido> detalle
) {

}
