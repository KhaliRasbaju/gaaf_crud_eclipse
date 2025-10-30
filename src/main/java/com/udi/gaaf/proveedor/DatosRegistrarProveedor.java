package com.udi.gaaf.proveedor;

import com.udi.gaaf.cuenta.DatosRegistrarCuenta;
import com.udi.gaaf.ubicacion.DatosRegistrarUbicacion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO utilizado para registrar o actualizar un proveedor.
 *
 * @param nit        Número de identificación tributaria del proveedor.
 * @param nombre     Nombre del proveedor.
 * @param telefono   Teléfono de contacto.
 * @param correo     Correo electrónico del proveedor.
 * @param ubicacion  Datos de la ubicación del proveedor.
 * @param cuenta     Datos de la cuenta asociada al proveedor.
 */
public record DatosRegistrarProveedor(
    @Positive
    @NotNull
    Long nit,

    @NotBlank
    String nombre,

    @NotBlank
    String telefono,

    @Email
    @NotBlank
    String correo,

    @NotNull
    @Valid
    DatosRegistrarUbicacion ubicacion,

    @NotNull
    @Valid
    DatosRegistrarCuenta cuenta
) {}
