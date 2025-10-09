package com.udi.gaaf.transaccion_inventario;


public record DatosDetalleTransaccion(
		Long id,
		TipoTransaccion tipo,
		Integer cantidad,
		String observacion,
		Long idPedido,
		String  producto,
		String bodega
) {

}
