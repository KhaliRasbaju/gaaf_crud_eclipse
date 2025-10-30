package com.udi.gaaf.proveedor;

import java.util.List;
import com.udi.gaaf.cuenta.DatosDetalleCuenta;
import com.udi.gaaf.ubicacion.DatosDetalleUbicacion;

/**
 * DTO que representa los detalles de un proveedor, incluyendo su información
 * básica, ubicaciones y cuentas asociadas.
 *
 * @param nit        Número de identificación tributaria del proveedor.
 * @param nombre     Nombre del proveedor.
 * @param telefono   Teléfono de contacto del proveedor.
 * @param correo     Correo electrónico del proveedor.
 * @param ubicacion  Lista de ubicaciones asociadas al proveedor.
 * @param cuenta     Lista de cuentas asociadas al proveedor.
 */
public record DatosDetalleProveedor(
    Long nit,
    String nombre,
    String telefono,
    String correo,
    List<DatosDetalleUbicacion> ubicacion,
    List<DatosDetalleCuenta> cuenta
) {}
