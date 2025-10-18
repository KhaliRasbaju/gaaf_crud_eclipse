package com.udi.gaaf.detalle_pedido;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.producto.ProductoService;

@Service
public class DetallePedidoService {
	@Autowired
	private DetallePedidoRepository repository;
	
	@Autowired
	private ProductoService productoService;
	

	private DatosDetalleDetallePedido detalleDetallePedido(DetallePedido detalle) {
		return new DatosDetalleDetallePedido(detalle.getHumedad(), detalle.getEstadoCacao(), detalle.getFermentacion(), detalle.getCantidad(), detalle.getPeso(), detalle.getProducto().getNombre());
	}

	private List<DetallePedido> obtenerDetallePedidoById(Long idPedido) {
		var detallePedidos = repository.findByPedido_Id(idPedido);
		System.out.println(idPedido);
		if(detallePedidos.isEmpty()) {
			throw new NotFoundException("No hay detalle del pedido por ese pedido");
		}
		return detallePedidos;
	}
	
	
	public DatosDetalleDetallePedido crear(DatosRegistrarDetallePedido datos, Pedido pedido) {
		var producto = productoService.obtenerProductoPorId(datos.idProducto());
		var detalle = new DetallePedido(datos, pedido, producto);
		var nuevoDetalle =  repository.save(detalle);
		return detalleDetallePedido(nuevoDetalle);
	}
	
	public DatosDetalleResponse editar(DatosRegistrarDetallePedido datos, Pedido pedido) {
	    var producto = productoService.obtenerProductoPorId(datos.idProducto());
	    var detalleExistente = repository.findByPedidoIdAndProductoId(pedido.getId(), producto.getId())
	            .orElseThrow(() -> new NotFoundException("Detalle no encontrado"));
	    
	    if(!detalleExistente.getProducto().equals(producto)) {
	    	repository.delete(detalleExistente);
	    	this.crear(datos, pedido);
	    	return new DatosDetalleResponse(200, "Producto del detalle cambiado correctamente");
	    }
	    	
	    if (detalleExistente.getHumedad() != datos.humedad()) detalleExistente.setHumedad(datos.humedad());
	    if (detalleExistente.getFermentacion() != datos.fermentacion()) detalleExistente.setFermentacion(datos.fermentacion());
	    if (detalleExistente.getEstadoCacao() != datos.estadoCacao()) detalleExistente.setEstadoCacao(datos.estadoCacao());
	    if (detalleExistente.getCantidad() != datos.cantidad()) detalleExistente.setCantidad(datos.cantidad());
	    if (detalleExistente.getPeso() != datos.peso()) detalleExistente.setPeso(datos.peso());
	    repository.save(detalleExistente);
	    return new DatosDetalleResponse(200, "Detalle del pedido actualizado correctamente");
	}

	
	public List<DatosDetalleDetallePedido> obtenerPorPedidoId(Long idPedido) {
		var detalle = obtenerDetallePedidoById(idPedido);
		return detalle.stream().map(d -> detalleDetallePedido(d)).toList();
	}
	
	public DatosDetalleResponse eliminarPorPedidoId(Long idPeido) {
		var detalle = obtenerDetallePedidoById(idPeido);
		repository.deleteAll(detalle);
		return new DatosDetalleResponse(200, "Detalle del pedido eliminado correctamente"); 
	}

}
