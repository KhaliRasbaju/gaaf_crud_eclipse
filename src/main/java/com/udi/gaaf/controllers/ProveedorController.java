package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotRequestBodyException;
import com.udi.gaaf.proveedor.DatosDetalleProveedor;
import com.udi.gaaf.proveedor.DatosRegistrarProveedor;
import com.udi.gaaf.proveedor.ProveedorService;

import jakarta.validation.Valid;

/**
 * Controlador REST que gestiona las operaciones CRUD relacionadas
 * con los proveedores en el sistema.
 * <p>
 * Permite registrar, actualizar, consultar, cambiar estado y eliminar
 * proveedores mediante el servicio {@link ProveedorService}.
 * </p>
 */
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    /**
     * Crea un nuevo proveedor en el sistema.
     *
     * @param datos objeto {@link DatosRegistrarProveedor} con la información del proveedor a registrar.
     * @return {@link ResponseEntity} con el detalle del proveedor creado y el código HTTP { @code 201 (Created) }.
     * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
     */
    @PostMapping("/crear")
    public ResponseEntity<DatosDetalleProveedor> crear(@RequestBody(required = false) @Valid DatosRegistrarProveedor datos) {
        if (datos == null) {
            throw new NotRequestBodyException("Se requiere el body");
        }
        var detalle = service.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
    }

    /**
     * Edita la información de un proveedor existente.
     *
     * @param datos objeto {@link DatosRegistrarProveedor} con los nuevos datos del proveedor.
     * @param nit número de identificación tributaria (NIT) del proveedor a editar.
     * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que contiene el resultado de la operación.
     * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
     */
    @PutMapping("/editar/{nit}")
    public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid DatosRegistrarProveedor datos, @PathVariable Long nit) {
        if (datos == null) {
            throw new NotRequestBodyException("Se requiere el body");
        }
        var detalle = service.editar(datos, nit);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Cambia el estado activo/inactivo de un proveedor.
     *
     * @param nit número de identificación tributaria (NIT) del proveedor.
     * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} 
     * que describe el resultado del cambio de estado.
     */
    @PutMapping("/estado/{nit}")
    public ResponseEntity<DatosDetalleResponse> cambiarEstado(@PathVariable Long nit) {
        var detalle = service.cambiarEstado(nit);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene la información de un proveedor según su NIT.
     *
     * @param nit número de identificación tributaria (NIT) del proveedor.
     * @return {@link ResponseEntity} con los datos detallados del proveedor.
     * @throws NotRequestBodyException si el NIT es nulo.
     */
    @GetMapping("/{nit}")
    public ResponseEntity<DatosDetalleProveedor> obtenerPorNit(@PathVariable Long nit) {
        if (nit == null) {
            throw new NotRequestBodyException("Se requiere nit del proveedor");
        }
        var detalle = service.obtenerPorNit(nit);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene la lista completa de proveedores registrados.
     *
     * @return {@link ResponseEntity} con una lista de {@link DatosDetalleProveedor}
     * y el código HTTP {@code 200 (OK)}.
     */
    @GetMapping
    public ResponseEntity<List<DatosDetalleProveedor>> obtenerTodos() {
        var proveedores = service.obtenerTodos();
        return ResponseEntity.ok(proveedores);
    }

    /**
     * Elimina un proveedor del sistema según su NIT.
     *
     * @param nit número de identificación tributaria (NIT) del proveedor a eliminar.
     * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} 
     * que indica el resultado de la eliminación.
     */
    @DeleteMapping("/{nit}")
    public ResponseEntity<DatosDetalleResponse> eliminarPorNit(@PathVariable Long nit) {
        var detalle = service.eliminarPorNit(nit);
        return ResponseEntity.ok(detalle);
    }
}
