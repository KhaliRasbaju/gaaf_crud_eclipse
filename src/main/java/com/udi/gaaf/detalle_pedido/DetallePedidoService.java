package com.udi.gaaf.detalle_pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {
	@Autowired
	private DetallePedidoRepository repository;
}
