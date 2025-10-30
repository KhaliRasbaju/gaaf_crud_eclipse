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
import com.udi.gaaf.entidad_bancaria.EntidadBancariaService;
import com.udi.gaaf.errors.NotRequestBodyException;

import jakarta.validation.Valid;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD de las entidades bancarias.
 * <p>
 * Proporciona endpoints para crear, editar, obtener y eliminar entidades bancarias,
 * así como para listar todas las disponibles en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/entidad-bancaria")
public class EntidadBancariaController {

	@Autowired
	private EntidadBancariaService service;

	/**
	 * Crea una nueva entidad bancaria.
	 *
	 * @param datos objeto {@link DatosRegistrarCommon} que contiene la información de la entidad bancaria a registrar.
	 * @return {@link ResponseEntity} con los datos detallados de la entidad bancaria creada y el código HTTP { @code 201 (Created) }.
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
	 * Edita una entidad bancaria existente.
	 *
	 * @param id identificador único de la entidad bancaria a editar.
	 * @param datos objeto {@link DatosRegistrarCommon} con los datos actualizados de la entidad bancaria.
	 * @return {@link ResponseEntity} con el resultado de la edición y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id o el cuerpo de la solicitud son nulos.
	 */
	@PutMapping("/editar/{id}")
	public ResponseEntity<DatosDetalleResponse> editar(@PathVariable Long id, @RequestBody(required = false) @Valid DatosRegistrarCommon datos) {
		if (datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id de la entidad bancaria");
		}
		var detalle = service.editar(id, datos);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene los detalles de una entidad bancaria específica según su identificador.
	 *
	 * @param id identificador único de la entidad bancaria.
	 * @return {@link ResponseEntity} con los datos detallados de la entidad bancaria y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleCommon> obtenerPorId(@PathVariable(required = false) Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id de la entidad bancaria");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene todas las entidades bancarias registradas en el sistema.
	 *
	 * @return {@link ResponseEntity} con la lista de entidades bancarias y el código HTTP { @code 200 (OK) }.
	 */
	@GetMapping
	public ResponseEntity<List<DatosDetalleCommon>> obtenerTodos() {
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Elimina una entidad bancaria por su identificador.
	 *
	 * @param id identificador único de la entidad bancaria a eliminar.
	 * @return {@link ResponseEntity} con el resultado de la eliminación y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable(required = false) Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id de la entidad bancaria");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
