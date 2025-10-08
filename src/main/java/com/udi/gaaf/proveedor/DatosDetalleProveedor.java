package com.udi.gaaf.proveedor;

import java.util.List;

import com.udi.gaaf.cuenta.DatosDetalleCuenta;
import com.udi.gaaf.ubicacion.DatosDetalleUbicacion;

public record DatosDetalleProveedor(
	Long nit,
	String nombre,
	String telefono,
	String correo,
	List<DatosDetalleUbicacion> ubicacion,
	List<DatosDetalleCuenta> cuenta
) {
	
}
