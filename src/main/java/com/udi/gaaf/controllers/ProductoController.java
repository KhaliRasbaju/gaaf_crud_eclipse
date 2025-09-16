package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.producto.DatosDetalleProducto;
import com.udi.gaaf.producto.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@GetMapping
	public ResponseEntity<List<DatosDetalleProducto>> findAll(){
		var productos = service.findAll();
		return ResponseEntity.ok(productos);
	}
}
