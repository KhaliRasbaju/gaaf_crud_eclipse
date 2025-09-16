package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.proveedor.DatosDetalleProveedor;
import com.udi.gaaf.proveedor.ProveedorService;


@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService service;
	
	
	
	@GetMapping()
	public ResponseEntity<List<DatosDetalleProveedor>> findAll(){
		var proveedores = service.findAll();
		return ResponseEntity.ok(proveedores);
	}
	
}
