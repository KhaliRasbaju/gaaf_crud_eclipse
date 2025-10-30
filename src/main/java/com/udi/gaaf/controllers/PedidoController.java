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
import com.udi.gaaf.pedido.DatosDetallePedido;
import com.udi.gaaf.pedido.DatosRegistrarPedido;
import com.udi.gaaf.pedido.PedidoService;

import jakarta.validation.Valid;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD
 * relacionadas con los pedidos dentro del sistema.
 * <p>
 * Permite crear, editar, recibir, consultar y eliminar pedidos
 * utilizando el servicio {@link PedidoService}.
 * </p>
 */
@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;

	/**
	 * Crea un nuevo pedido en el sistema.
	 *
	 * @param datos objeto {@link DatosRegistrarPedido} con la información del pedido a registrar.
	 * @return {@link ResponseEntity} con el detalle del pedido creado y el código HTTP { @code 201 (Created) }.
	 * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
	 */
	@PostMapping("/crear")
	public ResponseEntity<DatosDetallePedido> crear(@RequestBody(required = false) @Valid DatosRegistrarPedido datos) {
		if (datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}

	/**
	 * Edita un pedido existente según su identificador.
	 *
	 * @param datos objeto {@link DatosRegistrarPedido} con los nuevos datos del pedido.
	 * @param id identificador único del pedido a editar.
	 * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que contiene el resultado de la operación.
	 * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
	 */
	@PutMapping("/editar/{id}")
	public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid DatosRegistrarPedido datos, @PathVariable Long id) {
		if (datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Marca un pedido como recibido según su identificador.
	 *
	 * @param id identificador único del pedido a actualizar.
	 * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} que contiene el resultado de la operación.
	 * @throws NotRequestBodyException si el id del pedido es nulo.
	 */
	@PutMapping("/recibir/{id}")
	public ResponseEntity<DatosDetalleResponse> recibir(@PathVariable Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id del pedido");
		}
		var detalle = service.recibir(id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene los detalles de un pedido específico por su identificador.
	 *
	 * @param id identificador único del pedido.
	 * @return {@link ResponseEntity} con los datos del pedido y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetallePedido> obtenerPorId(@PathVariable Long id) {
		if (id == null) {
			throw new NotRequestBodyException("Se requiere el id del pedido");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Obtiene una lista de todos los pedidos registrados en el sistema.
	 *
	 * @return {@link ResponseEntity} con una lista de {@link DatosDetallePedido} 
	 * y el código HTTP { @code 200 (OK) }.
	 */
	@GetMapping
	public ResponseEntity<List<DatosDetallePedido>> obtenerTodos() {
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}

	/**
	 * Elimina un pedido del sistema según su identificador.
	 *
	 * @param id identificador único del pedido a eliminar.
	 * @return {@link ResponseEntity} con un {@link DatosDetalleResponse} 
	 * que contiene el resultado de la eliminación.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
