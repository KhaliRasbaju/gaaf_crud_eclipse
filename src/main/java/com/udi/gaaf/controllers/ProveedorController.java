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
import com.udi.gaaf.proveedor.DatosDetalleProveedor;
import com.udi.gaaf.proveedor.DatosRegistrarProveedor;
import com.udi.gaaf.proveedor.ProveedorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService service;
	
	@PostMapping("/crear")
	public ResponseEntity<DatosDetalleProveedor> crear(@RequestBody(required = false) @Valid DatosRegistrarProveedor datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere el body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	@PutMapping("/editar/{nit}")
	public ResponseEntity<DatosDetalleResponse> editar(@RequestBody(required = false) @Valid DatosRegistrarProveedor datos, @PathVariable Long nit){
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere el body");
		}
		var detalle = service.editar(datos, nit);
		return ResponseEntity.ok(detalle);
	}
	
	
	@PutMapping("/estado/{nit}")
	public ResponseEntity<DatosDetalleResponse> cambiarEstado(@PathVariable Long nit) {
		var detalle = service.cambiarEstado(nit);
		return ResponseEntity.ok(detalle);
	}
	
	
	@GetMapping("/{nit}")
	public ResponseEntity<DatosDetalleProveedor> obtenerPorNit(@PathVariable Long nit) {
		if(nit == null) {
			throw new NotRequestBodyException("Se requiere nit del proveedor");
		}
		var detalle = service.obtenerPorNit(nit);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping
	public ResponseEntity<List<DatosDetalleProveedor>> obtenerTodos(){
		var proveedores = service.obtenerTodos();
		return ResponseEntity.ok(proveedores);
	}
	
	@DeleteMapping("/{nit}")
	public ResponseEntity<DatosDetalleResponse> eliminarPorNit(@PathVariable Long nit){
		var detalle = service.eliminarPorNit(nit);
		return ResponseEntity.ok(detalle);
	}
}
