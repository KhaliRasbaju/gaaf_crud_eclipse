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

/**
 * Servicio que gestiona la lógica de negocio relacionada con los pedidos.
 */
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

    /**
     * Valida que un pedido no haya sido recibido previamente.
     *
     * @param pedido Pedido a validar.
     * @throws BadRequestException Si el pedido ya fue recibido.
     */
    private void validarRecibirPedido(Pedido pedido) {
        if (pedido.getRecibido()) {
            throw new BadRequestException("El pedido ya está recibido");
        }
    }

    /**
     * Convierte una entidad {@link Pedido} en un DTO {@link DatosDetallePedido}.
     *
     * @param pedido Entidad de pedido.
     * @return DTO con los detalles completos del pedido.
     */
    private DatosDetallePedido detallePedido(Pedido pedido) {
        var medio = medioPagoService.obtenerPorId(pedido.getPago().getId());
        var detallePedido = detallePedidoService.obtenerPorPedidoId(pedido.getId());
        return new DatosDetallePedido(
            pedido.getId(),
            pedido.getProveedor().getNit(),
            pedido.getValor(),
            pedido.getFechaPedido(),
            pedido.getRecibido(),
            pedido.getFechaEntrega(),
            medio,
            detallePedido
        );
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param id ID del pedido.
     * @return Pedido encontrado.
     * @throws NotFoundException Si no existe un pedido con el ID especificado.
     */
    public Pedido obtenerPedidoPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Pedido no encontrado por el id: " + id));
    }

    /**
     * Crea un nuevo pedido con su detalle y medio de pago.
     *
     * @param datos Datos del pedido a registrar.
     * @return DTO con los detalles del pedido creado.
     */
    @Transactional
    public DatosDetallePedido crear(DatosRegistrarPedido datos) {
        var proveedor = proveedorService.obtenerProveedorPorNit(datos.nitProveedor());
        var creaMedio = medioPagoService.crear(datos.medioPago());
        var medio = medioPagoService.obtenerMedioPagoPorId(creaMedio.id());
        var pedido = new Pedido(datos, proveedor, medio);
        var nuevoPedido = repository.save(pedido);
        datos.detalle().forEach(d -> detallePedidoService.crear(d, nuevoPedido));
        var pedidoFinal = obtenerPedidoPorId(nuevoPedido.getId());
        return detallePedido(pedidoFinal);
    }

    /**
     * Edita un pedido existente.
     *
     * @param datos Datos actualizados del pedido.
     * @param id    ID del pedido a editar.
     * @return Respuesta con el estado de la operación.
     * @throws BadRequestException Si el pedido ya fue recibido.
     */
    @Transactional
    public DatosDetalleResponse editar(DatosRegistrarPedido datos, Long id) {
        var pedido = obtenerPedidoPorId(id);
        validarRecibirPedido(pedido);

        var proveedor = proveedorService.obtenerProveedorPorNit(datos.nitProveedor());
        medioPagoService.editar(datos.medioPago(), id);

        if (!pedido.getValor().equals(datos.valor())) pedido.setValor(datos.valor());
        if (!pedido.getProveedor().getNit().equals(datos.nitProveedor())) pedido.setProveedor(proveedor);
        if (!pedido.getFechaPedido().equals(datos.fechaPedido())) pedido.setFechaPedido(datos.fechaPedido());

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
        return new DatosDetalleResponse(200, "Pedido actualizado correctamente");
    }

    /**
     * Marca un pedido como recibido y actualiza la fecha de entrega.
     *
     * @param id ID del pedido.
     * @return Respuesta con el estado de la operación.
     */
    @Transactional
    public DatosDetalleResponse recibir(Long id) {
        var pedido = obtenerPedidoPorId(id);
        validarRecibirPedido(pedido);
        pedido.setRecibido(true);
        pedido.setFechaEntrega(LocalDateTime.now());
        repository.saveAndFlush(pedido);
        return new DatosDetalleResponse(200, "Pedido recibido correctamente");
    }

    /**
     * Obtiene los detalles de un pedido por ID.
     *
     * @param id ID del pedido.
     * @return DTO con los detalles del pedido.
     */
    public DatosDetallePedido obtenerPorId(Long id) {
        var pedido = obtenerPedidoPorId(id);
        return detallePedido(pedido);
    }

    /**
     * Obtiene todos los pedidos registrados.
     *
     * @return Lista de DTOs con los pedidos.
     */
    public List<DatosDetallePedido> obtenerTodos() {
        var pedidos = repository.findAll();
        return pedidos.stream().map(this::detallePedido).toList();
    }

    /**
     * Elimina un pedido por ID, si no tiene transacciones asociadas.
     *
     * @param id ID del pedido.
     * @return Respuesta con el estado de la operación.
     * @throws BadRequestException Si el pedido ya fue recibido o tiene transacciones.
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var pedido = obtenerPedidoPorId(id);
        validarRecibirPedido(pedido);

        if (!pedido.getTransaccionInventarios().isEmpty()) {
            throw new BadRequestException("No se puede eliminar: existen transacciones asociadas");
        }
        
        try {
        	pedido.getDetallePedidos().forEach(d -> {
        		detallePedidoService.eliminarPorPedidoId(pedido.getId());
        	});
        	medioPagoService.eliminarPorId(pedido.getPago().getId());
        	pedido.setDetallePedidos(null);
        	pedido.setPago(null);
        	repository.delete(pedido);
		} catch (Exception e) {
			throw new BadRequestException("Error al eliminar");
		}
        
        return new DatosDetalleResponse(200, "Pedido eliminado correctamente");
    }
}
