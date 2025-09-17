package com.udi.gaaf.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DatosDetallePedido(

	Long nitProveedor,
	List<String> productos,
	Integer cantidad,
	BigDecimal valor,
	LocalDateTime fechaPedido,
	Boolean recibido,
	LocalDateTime fechaEntrega
		
) {

}
