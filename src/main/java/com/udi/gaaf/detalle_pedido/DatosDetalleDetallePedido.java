package com.udi.gaaf.detalle_pedido;

import java.util.List;




public record DatosDetalleDetallePedido(
	Long idPedido,
	Float humedad,
	Float estadoCacao,
	Float fermentacion,
	Integer cantidad,
	Float peso,
	List<String> productos
) {

}
