package com.udi.gaaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.inventario.DatosReporte;
import com.udi.gaaf.inventario.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
	
	@Autowired
	private InventarioService service;

	
	@GetMapping("/reporte")
	public ResponseEntity<DatosReporte> getReporte() {
		return ResponseEntity.ok(service.getReporte());
	}
	
}
