package com.udi.gaaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotRequestBodyException;
import com.udi.gaaf.transaccion_inventario.DatosRegistrarTransaccion;
import com.udi.gaaf.transaccion_inventario.TransaccionInventarioService;

import jakarta.validation.Valid;

/**
 * Controlador REST encargado de gestionar las transacciones de inventario
 * dentro del sistema GAAF.
 * <p>
 * Este controlador permite registrar nuevas transacciones relacionadas con
 * el movimiento de productos en el inventario (entradas o salidas).
 * </p>
 */
@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private TransaccionInventarioService service;

    /**
     * Crea una nueva transacción de inventario.
     * <p>
     * Este endpoint permite registrar un movimiento de inventario, el cual
     * puede representar una entrada o una salida de productos dependiendo
     * del tipo de transacción especificado.
     * </p>
     *
     * @param datos Objeto {@link DatosRegistrarTransaccion} que contiene la información
     *              necesaria para registrar la transacción (producto, cantidad,
     *              tipo de movimiento, bodega, etc.).
     * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que confirma
     *         la creación de la transacción.
     *         <p>Retorna un código HTTP { @code 201 (Created) } si la operación es exitosa.</p>
     * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
     */
    @PostMapping("/crear")
    public ResponseEntity<DatosDetalleResponse> crear(@RequestBody(required = false) @Valid DatosRegistrarTransaccion datos) {
        if (datos == null) {
            throw new NotRequestBodyException("Se requiere body");
        }
        var detalle = service.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
    }
}
