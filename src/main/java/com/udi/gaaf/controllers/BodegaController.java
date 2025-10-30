package com.udi.gaaf.controllers;

import com.udi.gaaf.bodega.BodegaService;
import com.udi.gaaf.bodega.DatosDetalleBodega;
import com.udi.gaaf.bodega.DatosRegistrarBodega;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotRequestBodyException;
import jakarta.validation.Valid;
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

/**
 * Controlador REST para la gestión de bodegas.
 * 
 * <p>Proporciona endpoints para crear, editar, obtener y eliminar registros de bodegas.</p>
 */
@RestController
@RequestMapping("/bodega")
public class BodegaController {

	@Autowired
	private BodegaService service;
	
	/**
	 * Crea una nueva bodega en el sistema.
	 *
	 * @param datos objeto {@link DatosRegistrarBodega} con la información necesaria para registrar una bodega.
	 * @return {@link ResponseEntity} con el detalle de la bodega creada y el código HTTP { @code 201 (Created) }.
	 * @throws NotRequestBodyException si el cuerpo de la solicitud es nulo.
	 */
	@PostMapping("/crear")
	public ResponseEntity<DatosDetalleBodega> crear(@RequestBody(required = false) @Valid DatosRegistrarBodega datos) {
		
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	/**
	 * Edita los datos de una bodega existente.
	 *
	 * @param datos objeto {@link DatosRegistrarBodega} con los nuevos datos de la bodega.
	 * @param id identificador único de la bodega a editar.
	 * @return {@link ResponseEntity} con el resultado de la operación y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el cuerpo de la solicitud o el id son nulos.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid DatosRegistrarBodega datos, @PathVariable Long id) {
		if(datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}
	
	/**
	 * Obtiene los datos detallados de una bodega según su identificador.
	 *
	 * @param id identificador único de la bodega.
	 * @return {@link ResponseEntity} con los datos de la bodega y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleBodega> obtenerPorId(@PathVariable Long id) {
		
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la bodega");
		}
		
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	/**
	 * Obtiene la lista de todas las bodegas registradas en el sistema.
	 *
	 * @return {@link ResponseEntity} con la lista de bodegas y el código HTTP { @code 200 (OK) }.
	 */
	@GetMapping
	public ResponseEntity<List<DatosDetalleBodega>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	/**
	 * Elimina una bodega existente según su identificador.
	 *
	 * @param id identificador único de la bodega a eliminar.
	 * @return {@link ResponseEntity} con el resultado de la eliminación y el código HTTP { @code 200 (OK) }.
	 * @throws NotRequestBodyException si el id es nulo.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la bodega");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
