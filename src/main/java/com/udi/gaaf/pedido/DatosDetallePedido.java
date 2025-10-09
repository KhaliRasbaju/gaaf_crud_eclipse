package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.udi.gaaf.detalle_pedido.DatosDetalleDetallePedido;
import com.udi.gaaf.medio_pago.DatosDetalleMedioPago;

public record DatosDetallePedido(
	Long id,
	Long nitProveedor,
	Double valor,
	LocalDateTime fechaPedido,
	Boolean recibido,
	LocalDateTime fechaEntrega,
	DatosDetalleMedioPago medioPago,
	List<DatosDetalleDetallePedido> detallePedido
	
) {

}
