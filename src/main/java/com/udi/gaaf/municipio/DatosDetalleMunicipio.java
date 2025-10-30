package com.udi.gaaf.municipio;

/**
 * DTO que representa los detalles de un municipio.
 *
 * @param id          Identificador único del municipio.
 * @param nombre      Nombre del municipio.
 * @param departameto Nombre del departamento al que pertenece el municipio.
 */
public record DatosDetalleMunicipio(
    Long id,
    String nombre,
    String departameto
) {}
