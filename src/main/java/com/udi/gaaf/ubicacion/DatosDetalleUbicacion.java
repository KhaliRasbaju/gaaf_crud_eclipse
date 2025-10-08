package com.udi.gaaf.ubicacion;

import com.udi.gaaf.municipio.DatosDetalleMunicipio;

public record DatosDetalleUbicacion(
	Long id,
	String direccion,
	DatosDetalleMunicipio municipio
) {
	
	

}
