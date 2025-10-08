package com.udi.gaaf.cuenta;

import com.udi.gaaf.common.DatosDetalleCommon;

public record DatosDetalleCuenta(
	Long id,
	String numero,
	TipoCuenta tipo,
	DatosDetalleCommon entidad
) {

}
