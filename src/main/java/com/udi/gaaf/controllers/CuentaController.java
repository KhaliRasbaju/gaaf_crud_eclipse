package com.udi.gaaf.controllers;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.cuenta.CuentaService;
import com.udi.gaaf.cuenta.DatosDetalleCuenta;
import com.udi.gaaf.cuenta.DatosRegistrarCuenta;
import com.udi.gaaf.errors.NotRequestBodyException;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

	@Autowired
	private CuentaService service;
	
	public ResponseEntity<DatosDetalleCuenta>  crear(@RequestBody @Valid DatosRegistrarCuenta datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	
	public ResponseEntity<DatosDetalleCuenta> editar(@RequestBody @Valid  DatosRegistrarCuenta datos, @PathVariable Long id) {
		if(datos == null || id == null) {
			throw new NotRequestBodyException("Se requiere body y el id");
		}
		var  detalle = service.editar(datos, id);
		return ResponseEntity.ok(detalle);
	}
	
	
	public ResponseEntity<DatosDetalleCuenta> obtenerPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la cuenta");
		}
		var  detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	
	public ResponseEntity<List<DatosDetalleCuenta>> obtenerTodos(){
		var  detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	
	public  ResponseEntity<DatosDetalleResponse> eliminarPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id de la cuenta");
		}
		var  detalle = service.eliminarPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
}
