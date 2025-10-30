package com.udi.gaaf.ubicacion;

import com.udi.gaaf.municipio.DatosDetalleMunicipio;

/**
 * Record que representa los datos detallados de una ubicación asociada a un proveedor.
 *
 * @param id         Identificador único de la ubicación.
 * @param direccion  Dirección física de la ubicación.
 * @param municipio  Municipio al que pertenece la ubicación.
 */
public record DatosDetalleUbicacion(
	Long id,
	String direccion,
	DatosDetalleMunicipio municipio
) {}
