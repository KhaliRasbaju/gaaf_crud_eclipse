package com.udi.gaaf.detalle_pedido;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.producto.DatosDetalleProducto;
import com.udi.gaaf.producto.ProductoService;

@Service
public class DetallePedidoService {
	@Autowired
	private DetallePedidoRepository repository;
	
	@Autowired
	private ProductoService productoService;
	
	public List<DatosDetalleDetallePedido> findAll() {
	    var detallesPedidos = repository.findAll();
	
	    var idsProductos = detallesPedidos.stream()
	            .map(dp -> dp.getProducto().getId())
	            .toList();
	
	    
	    var productos = productoService.findAllByIds(idsProductos);
	    var productosMap = productos.stream()
	            .collect(Collectors.toMap(DatosDetalleProducto::id, DatosDetalleProducto::nombre));
	
	    
	    Map<Long, List<String>> productosPorPedido = detallesPedidos.stream()
	            .collect(Collectors.groupingBy(
	                    dp -> dp.getPedido().getId(),
	                    Collectors.mapping(dp -> productosMap.get(dp.getProducto().getId()), Collectors.toList())
	            ));
	
	    
	    return detallesPedidos.stream()
	            .collect(Collectors.groupingBy(dp -> dp.getPedido().getId()))
	            .entrySet().stream()
	            .map(entry -> {
	                var first = entry.getValue().get(0); // tomo un detalle como referencia
	                return new DatosDetalleDetallePedido(
	                        first.getPedido().getId(),
	                        first.humedad,
	                        first.estadoCacao,
	                        first.fermentacion,
	                        first.cantidad,
	                        first.peso,
	                        productosPorPedido.get(first.getPedido().getId())
	                );
	            })
	            .toList();
}


}
