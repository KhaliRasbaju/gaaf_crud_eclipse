package com.udi.gaaf.pedido;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.detalle_pedido.DetallePedidoService;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.medio_pago.MedioPagoService;
import com.udi.gaaf.proveedor.ProveedorService;


@Service
public class PedidoService {
	

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private DetallePedidoService detallePedidoService;
	
	@Autowired
	private MedioPagoService medioPagoService;

	private void validarRecibirPedido(Pedido pedido) {
		if(pedido.getRecibido()) {
			throw new BadRequestException("El pedido ya esta recibido");
		}
	}
	
	private DatosDetallePedido detallePedido(Pedido pedido) {
		var medio = medioPagoService.obtenerPorId(pedido.getPago().getId());
		var detallePedido = detallePedidoService.obtenerPorPedidoId(pedido.getId()); 
		return new DatosDetallePedido(pedido.getId(), pedido.getProveedor().getNit(), pedido.getValor(), pedido.getFechaPedido(), pedido.getRecibido(), pedido.getFechaEntrega(), medio, detallePedido);
	}
	
	
	public Pedido obtenerPedidoPorId(Long id) {
		return repository.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado por el id: "+id));
	}
	
	
	
	
	@Transactional
	public DatosDetallePedido crear(DatosRegistrarPedido datos) {
		var proveedor = proveedorService.obtenerProveedorPorNit(datos.nitProveedor());
		var creaMedio = medioPagoService.crear(datos.medioPago());
		var medio = medioPagoService.obtenerMedioPagoPorId(creaMedio.id());
		var pedido = new Pedido(datos, proveedor, medio);
		var nuevoPedido = repository.save(pedido);
		datos.detalle().stream().forEach(d -> detallePedidoService.crear(d, nuevoPedido));
		var pedidoFinal = obtenerPedidoPorId(nuevoPedido.getId());
		return detallePedido(pedidoFinal);
	}
	
	@Transactional
	public DatosDetalleResponse editar(DatosRegistrarPedido datos, Long id) {
		var pedido = obtenerPedidoPorId(id);
		validarRecibirPedido(pedido);
		var proveedor = proveedorService.obtenerProveedorPorNit(datos.nitProveedor());
		medioPagoService.editar(datos.medioPago(), id);
		if(pedido.getValor() != datos.valor()) pedido.setValor(datos.valor());
		if(pedido.getProveedor().getNit() != datos.nitProveedor()) pedido.setProveedor(proveedor);
		if(pedido.getFechaPedido() != datos.fechaPedido()) pedido.setFechaPedido(datos.fechaPedido());
		pedido.setRecibido(false);
		for (var detalleNuevo : datos.detalle()) {
		    var detalleExistente = pedido.getDetallePedidos().stream()
		        .filter(d -> d.getProducto().getId().equals(detalleNuevo.idProducto()))
		        .findFirst()
		        .orElse(null);

		    if (detalleExistente != null) {
		        detallePedidoService.editar(detalleNuevo, pedido);
		    } else {
		        detallePedidoService.crear(detalleNuevo, pedido);
		    }
		}
		
		repository.save(pedido);
		return new DatosDetalleResponse(200, "Pedido cambiado");
	}
	
	
	@Transactional
	public DatosDetalleResponse recibir(Long id) {
		var pedido = obtenerPedidoPorId(id);
		validarRecibirPedido(pedido);
		pedido.setRecibido(true);
		pedido.setFechaEntrega(LocalDateTime.now());
		repository.saveAndFlush(pedido);
		return new DatosDetalleResponse(200, "Pedido recibido correctamente");
	}
	
	
	public DatosDetallePedido obtenerPorId(Long id) {
		var pedido = obtenerPedidoPorId(id);
		return detallePedido(pedido);
	}

	public List<DatosDetallePedido> obtenerTodos(){
		var pedidos = repository.findAll();
		return pedidos.stream().map((d) -> detallePedido(d)).toList();
	}
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var pedido = obtenerPedidoPorId(id);
		validarRecibirPedido(pedido);
		if(pedido.getTransaccionInventarios() != null) {
			throw new BadRequestException("No se pude eliminar ya hay transacciones asociadas");
		}
		detallePedidoService.eliminarPorPedidoId(pedido.getId());
		repository.delete(pedido);
		return new DatosDetalleResponse(200, "Pedido eliminado correctamente");
	}
}
