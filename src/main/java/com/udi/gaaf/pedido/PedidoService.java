package com.udi.gaaf.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.producto.ProductoRepository;

@Service
public class PedidoService {

	@Autowired
	private ProductoRepository repository;
}
