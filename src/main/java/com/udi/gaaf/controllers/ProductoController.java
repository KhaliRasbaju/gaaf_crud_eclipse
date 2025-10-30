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
import com.udi.gaaf.producto.DatosDetalleProducto;
import com.udi.gaaf.producto.DatosRegistrarProducto;
import com.udi.gaaf.producto.ProductoService;

import jakarta.validation.Valid;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD
 * relacionadas con los productos en el sistema de inventario.
 * <p>
 * Permite crear, editar, consultar y eliminar productos, así como
 * obtener listados generales mediante el servicio {@link ProductoService}.
 * </p>
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService service;

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param datos objeto {@link DatosRegistrarProducto} con la información del producto a registrar.
     * @return {@link ResponseEntity} con el detalle del producto creado y el código HTTP { @code 201 (Created) }.
     * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
     */
    @PostMapping("/crear")
    public ResponseEntity<DatosDetalleProducto> crear(@RequestBody(required = false) @Valid DatosRegistrarProducto datos) {
        if (datos == null) {
            throw new NotRequestBodyException("Se requiere body");
        }
        var detalle = service.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
    }

    /**
     * Edita un producto existente según su identificador.
     *
     * @param datos objeto {@link DatosRegistrarProducto} con los nuevos datos del producto.
     * @param id identificador único del producto a editar.
     * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que contiene el resultado de la operación.
     * @throws NotRequestBodyException si el cuerpo o el id son nulos.
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid DatosRegistrarProducto datos, @PathVariable Long id) {
        if (datos == null || id == null) {
            throw new NotRequestBodyException("Se requiere body y el id del producto");
        }
        var detalle = service.editar(datos, id);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene los detalles de un producto específico por su identificador.
     *
     * @param id identificador único del producto.
     * @return {@link ResponseEntity} con los datos del producto y el código HTTP { @code 200 (OK) }.
     * @throws NotRequestBodyException si el id es nulo.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleProducto> obtenerPorId(@PathVariable Long id) {
        if (id == null) {
            throw new NotRequestBodyException("Se requiere el id del producto");
        }
        var detalle = service.obtenerPorId(id);
        return ResponseEntity.ok(detalle);
    }

    /**
     * Obtiene una lista de todos los productos registrados en el sistema.
     *
     * @return {@link ResponseEntity} con una lista de {@link DatosDetalleProducto}
     * y el código HTTP {@code 200 (OK)}.
     */
    @GetMapping
    public ResponseEntity<List<DatosDetalleProducto>> obtenerTodos() {
        var detalle = service.obtenerTodos();
        return ResponseEntity.ok(detalle);
    }

    /**
     * Elimina un producto del sistema según su identificador.
     *
     * @param id identificador único del producto a eliminar.
     * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} 
     * que contiene el resultado de la eliminación.
     * @throws NotRequestBodyException si el id es nulo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
        if (id == null) {
            throw new NotRequestBodyException("Se requiere el id del producto");
        }
        var detalle = service.eliminarPorId(id);
        return ResponseEntity.ok(detalle);
    }
}
