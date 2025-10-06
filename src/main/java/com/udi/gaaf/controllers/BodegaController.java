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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bodega")
public class BodegaController {

	@Autowired
	private BodegaService service;
	
	
	
	
	public ResponseEntity<DatosDetalleBodega> crear(@RequestBody(required = false) @Valid DatosRegistrarBodega datos) {
		
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	public ResponseEntity<DatosDetalleBodega> editar(@RequestBody(required = false) @Valid  DatosRegistrarBodega datos, @PathVariable Long id) {
		if(datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}
	
	
	
	public ResponseEntity<DatosDetalleBodega> obtenerPorId(@PathVariable Long id) {
		
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la bodega");
		}
		
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	

	public ResponseEntity<List<DatosDetalleBodega>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	
	
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la bodega");
		}
		
		
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
