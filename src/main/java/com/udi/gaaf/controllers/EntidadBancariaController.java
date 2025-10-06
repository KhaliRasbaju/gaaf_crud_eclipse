package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.entidad_bancaria.DatosDetalleEntidadBancaria;
import com.udi.gaaf.entidad_bancaria.DatosRegistrarEntidadBancaria;
import com.udi.gaaf.entidad_bancaria.EntidadBancariaService;
import com.udi.gaaf.errors.NotRequestBodyException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/entidad-bancaria")
public class EntidadBancariaController {

	@Autowired
	private EntidadBancariaService service;
	
	
	public ResponseEntity<DatosDetalleEntidadBancaria> crear(@RequestBody(required = false) @Valid DatosRegistrarEntidadBancaria datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	
	public ResponseEntity<DatosDetalleEntidadBancaria> editar(@PathVariable Long id, @RequestBody(required = false) @Valid DatosRegistrarEntidadBancaria datos) {
		if(datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id de la entidad bancaria");
		}
		var detalle = service.editar(id, datos);
		return ResponseEntity.ok(detalle);
	}
	
	public ResponseEntity<DatosDetalleEntidadBancaria> obtenerPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la entidad bancaria");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	public ResponseEntity<List<DatosDetalleEntidadBancaria>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la entidad bancaria");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	
}
