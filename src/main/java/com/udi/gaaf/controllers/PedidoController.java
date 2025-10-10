package com.udi.gaaf.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotRequestBodyException;
import com.udi.gaaf.pedido.DatosDetallePedido;
import com.udi.gaaf.pedido.DatosRegistrarPedido;
import com.udi.gaaf.pedido.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@PostMapping("/crear")
	public  ResponseEntity<DatosDetallePedido> crear(@RequestBody(required = false) @Valid DatosRegistrarPedido datos) {
		if(datos == null) {
			throw new NotRequestBodyException("Se requiere body");
		}
		var detalle = service.crear(datos);
		return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
	}
	
	@PutMapping("/recibir/{id}")
	public  ResponseEntity<DatosDetalleResponse> recibir(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id del pedido");
		}
		var detalle = service.recibir(id);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosDetallePedido> obtenerPorId(@PathVariable Long id) {
		if(id == null) {
			throw new NotRequestBodyException("Se requiere el id del pedido");
		}
		var detalle = service.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping
	public ResponseEntity<List<DatosDetallePedido>> obtenerTodos(){
		var detalle = service.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
}
