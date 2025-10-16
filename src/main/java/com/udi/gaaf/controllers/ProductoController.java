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

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	
	@PostMapping("/crear")
	public ResponseEntity<DatosDetalleProducto> crear(@RequestBody(required = false) @Valid DatosRegistrarProducto datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid  DatosRegistrarProducto datos, @PathVariable Long id) {
		if(datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id del producto");
		}
		var detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleProducto> obtenerPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id del producto");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
		
	}
	
	@GetMapping
	public ResponseEntity<List<DatosDetalleProducto>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id del producto");
		}
		var detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
}
