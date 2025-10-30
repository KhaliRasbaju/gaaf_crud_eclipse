package com.udi.gaaf.ubicacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Record que contiene los datos necesarios para registrar una nueva ubicación.
 * Se utiliza al crear o actualizar una ubicación de proveedor.
 *
 * @param direccion   Dirección física de la ubicación.
 * @param idMunicipio Identificador del municipio asociado.
 */
public record DatosRegistrarUbicacion(
	@NotBlank
	String direccion,
	@Positive
	@NotNull
	Long idMunicipio
) {}
