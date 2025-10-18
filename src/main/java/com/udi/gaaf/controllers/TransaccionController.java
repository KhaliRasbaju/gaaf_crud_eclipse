package com.udi.gaaf.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotRequestBodyException;
import com.udi.gaaf.transaccion_inventario.DatosRegistrarTransaccion;
import com.udi.gaaf.transaccion_inventario.TransaccionInventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

	@Autowired
	private TransaccionInventarioService service;
	
	@PostMapping("/crear")
	public ResponseEntity<DatosDetalleResponse> crear(@RequestBody(required = false) @Valid DatosRegistrarTransaccion datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
}
