package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.errors.NotRequestBodyException;
import com.udi.gaaf.inventario.DatosDetalleInventario;
import com.udi.gaaf.inventario.Inventario;
import com.udi.gaaf.inventario.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
	
	@Autowired
	private InventarioService service;
	



	@GetMapping
	public ResponseEntity<List<DatosDetalleInventario>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Inventario>> obtenerInventarioPorBodegaId(@PathVariable Long id)  {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la bodega");
		}
		var detalle = service.obtenerInventarioPorBodegaId(id);
		return ResponseEntity.ok(detalle);
	}
	
	

	
}
