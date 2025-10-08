package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	
	@GetMapping("/{nit}")
	public ResponseEntity<DatosDetalleProveedor> obtenerPorNit(@PathVariable Long nit) {
		var detalle = service.obtenerPorNit(nit);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping
	public ResponseEntity<List<DatosDetalleProveedor>> findAll(){
		var proveedores = service.findAll();
		return ResponseEntity.ok(proveedores);
	}
	
}
