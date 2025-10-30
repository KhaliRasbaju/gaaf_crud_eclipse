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

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.errors.NotRequestBodyException;
import com.udi.gaaf.metodo_pago.MetodoPagoService;

import jakarta.validation.Valid;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD 
 * relacionadas con los métodos de pago.
 * <p>
 * Proporciona endpoints para crear, editar, consultar y eliminar
 * métodos de pago dentro del sistema.
 * </p>
 */
@RestController
@RequestMapping("/metodo-pago")
public class MetodoPagoController {

	@Autowired
	private MetodoPagoService service;

	/**
	 * Crea un nuevo método de pago en el sistema.
	 *
	 * @param datos objeto {@link DatosRegistrarCommon} con la información del método de pago a registrar.
	 * @return {@link ResponseEntity} con los datos del método de pago creado y el código HTTP { @code 201 (Created) }.
	 * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
	 */
	@PostMapping("/crear")
	public ResponseEntity<DatosDetalleCommon> crear(@RequestBody(required = false) @Valid DatosRegistrarCommon datos) {
		if (datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}

	/**
	 * Edita un método de pago existente.
	 *
	 * @param datos objeto {@link DatosRegistrarCommon} con la información actualizada del método de pago.
	 * @param id identificador único del método de pago a editar.
	 * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que contiene el resultado de la operación.
	 * @throws NotRequestBodyException si el id o el cuerpo de la solicitud son nulos.
	 */
	@PutMapping("/editar/{id}")
	public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid DatosRegistrarCommon datos, @PathVariable Long id) {
		if (datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id del metodo de pago");
		}
		var detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene los detalles de un método de pago según su identificador.
	 *
	 * @param id identificador único del método de pago.
	 * @return {@link ResponseEntity} con los datos del método de pago y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleCommon> obtenerPorId(@PathVariable Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id del metodo de pago");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene todos los métodos de pago registrados en el sistema.
	 *
	 * @return {@link ResponseEntity} con una lista de {@link DatosDetalleCommon} 
	 * y el código HTTP {@code 200 (OK)}.
	 */
	@GetMapping
	public ResponseEntity<List<DatosDetalleCommon>> obtenerTodos() {
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Elimina un método de pago según su identificador.
	 *
	 * @param id identificador único del método de pago a eliminar.
	 * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que contiene 
	 * el resultado de la eliminación y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id del metodo de pago");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
