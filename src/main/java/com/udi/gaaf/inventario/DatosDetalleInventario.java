package com.udi.gaaf.inventario;

import java.time.LocalDateTime;

public record DatosDetalleInventario(
	Integer cantidad,
	LocalDateTime fecha,
	String bodega,
	String producto
	
) {

}
