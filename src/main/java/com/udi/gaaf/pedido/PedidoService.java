package com.udi.gaaf.pedido;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.detalle_pedido.DetallePedidoService;
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

	
	private DatosDetallePedido detallePedido(Pedido pedido) {
		var medio = medioPagoService.obtenerPorId(pedido.getPago().getId());
		var detallePedido = detallePedidoService.obtenerPorPedidoId(pedido.getId()); 
		return new DatosDetallePedido(pedido.getId(), pedido.getProveedor().getNit(), pedido.getValor(), pedido.getFechaPedido(), pedido.getRecibido(), pedido.getFechaEntrega(), medio, detallePedido);
	}
	
	
	public Pedido obtenerPedidoPorId(Long id) {
		return repository.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado por el id: "+id));
	}
	
	public DatosDetallePedido crear(DatosRegistrarPedido datos) {
		var proveedor = proveedorService.obtenerProveedorPorNit(datos.nitProveedor());
		var pedido = new Pedido(datos, proveedor);
		var nuevoPedido = repository.save(pedido);
		medioPagoService.crear(datos.medioPago(), nuevoPedido);
		datos.detalle().stream().forEach(d -> detallePedidoService.crear(d, nuevoPedido));
		var pedidoFinal = obtenerPedidoPorId(nuevoPedido.getId());
		return detallePedido(pedidoFinal);
	}
	
	public DatosDetalleResponse recibir(Long id) {
		var pedido = obtenerPedidoPorId(id);
		pedido.setRecibido(true);
		pedido.setFechaEntrega(LocalDateTime.now());;
		repository.save(pedido);
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
}
