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

@RestController
@RequestMapping("/entidad-bancaria")
public class EntidadBancariaController {

	@Autowired
	private EntidadBancariaService service;
	
	
	@PostMapping("/crear")
	public ResponseEntity<DatosDetalleCommon> crear(@RequestBody(required = false) @Valid DatosRegistrarCommon datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<DatosDetalleCommon> editar(@PathVariable Long id, @RequestBody(required = false) @Valid DatosRegistrarCommon datos) {
		if(datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id de la entidad bancaria");
		}
		var detalle = service.editar(id, datos);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleCommon> obtenerPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la entidad bancaria");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping
	public ResponseEntity<List<DatosDetalleCommon>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la entidad bancaria");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	
}
