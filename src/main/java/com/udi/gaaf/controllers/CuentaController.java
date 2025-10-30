package com.udi.gaaf.controllers;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.cuenta.CuentaService;
import com.udi.gaaf.cuenta.DatosDetalleCuenta;
import com.udi.gaaf.cuenta.DatosRegistrarCuenta;
import com.udi.gaaf.errors.NotRequestBodyException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de cuentas bancarias de proveedores.
 *
 * <p>Proporciona endpoints para editar, obtener y eliminar cuentas,
 * así como para listar todas las cuentas registradas en el sistema.</p>
 */
@RestController
@RequestMapping("/cuenta")
public class CuentaController {

	@Autowired
	private CuentaService service;

	/**
	 * Edita una cuenta existente en el sistema.
	 *
	 * @param datos objeto {@link DatosRegistrarCuenta} que contiene la información actualizada de la cuenta.
	 * @param id identificador único de la cuenta que se desea editar.
	 * @return {@link ResponseEntity} con el resultado de la operación y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el cuerpo de la solicitud o el id son nulos.
	 */
	@PutMapping("/editar/{id}")
	public ResponseEntity<DatosDetalleResponse> editar(@RequestBody @Valid DatosRegistrarCuenta datos, @PathVariable Long id) {
		if (datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id");
		}
		var detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene los detalles de una cuenta específica por su identificador.
	 *
	 * @param id identificador único de la cuenta.
	 * @return {@link ResponseEntity} con los datos detallados de la cuenta y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleCuenta> obtenerPorId(@PathVariable Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id de la cuenta");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene la lista completa de cuentas registradas.
	 *
	 * @return {@link ResponseEntity} con la lista de todas las cuentas y el código HTTP { @code 200 (OK) }.
	 */
	@GetMapping
	public ResponseEntity<List<DatosDetalleCuenta>> obtenerTodos() {
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Elimina una cuenta por su identificador.
	 *
	 * @param id identificador único de la cuenta a eliminar.
	 * @return {@link ResponseEntity} con el resultado de la operación y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@DeleteMapping("/id")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id de la cuenta");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
