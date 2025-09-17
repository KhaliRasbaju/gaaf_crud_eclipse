package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.pedido.DatosDetallePedido;
import com.udi.gaaf.pedido.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	
	@GetMapping
	public ResponseEntity<List<DatosDetallePedido>> findAll(){
		var detallePedido = service.findAll();
		return ResponseEntity.ok(detallePedido);
	}
}
