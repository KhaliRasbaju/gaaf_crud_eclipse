package com.udi.gaaf.pedido;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.detalle_pedido.DetallePedido;
import com.udi.gaaf.detalle_pedido.DetallePedidoService;
import com.udi.gaaf.producto.ProductoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private DetallePedidoService detallePedidoService;
	
	public List<DatosDetallePedido> findAll(){
		var pedidos = repository.findAll();
		var detallePedidos = detallePedidoService.findAll();
		return pedidos.stream()
				.flatMap(pd -> detallePedidos.stream()
						.filter(dp -> pd.getId().equals(dp.idPedido()))
						.map(dp ->  new DatosDetallePedido(pd.getProveedor().getNit(), dp.productos(),dp.cantidad(), BigDecimal.valueOf(pd.getValor()), pd.getFechaPedido(), pd.getRecibido(),pd.getFechaEntrega()))
					).toList();
	}
}
