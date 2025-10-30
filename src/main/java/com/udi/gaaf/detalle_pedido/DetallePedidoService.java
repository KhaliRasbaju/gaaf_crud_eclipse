package com.udi.gaaf.detalle_pedido;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.producto.ProductoService;

/**
 * Servicio que gestiona la lógica de negocio para los detalles de los pedidos.
 * Permite crear, editar, obtener y eliminar detalles asociados a un pedido.
 */
@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repository;

    @Autowired
    private ProductoService productoService;

    /**
     * Convierte una entidad {@link DetallePedido} en un DTO de respuesta.
     * 
     * @param detalle Entidad del detalle.
     * @return DTO con los datos del detalle.
     */
    private DatosDetalleDetallePedido detalleDetallePedido(DetallePedido detalle) {
        return new DatosDetalleDetallePedido(
            detalle.getHumedad(),
            detalle.getEstadoCacao(),
            detalle.getFermentacion(),
            detalle.getCantidad(),
            detalle.getPeso(),
            detalle.getProducto().getNombre()
        );
    }

    /**
     * Obtiene todos los detalles asociados a un pedido.
     * 
     * @param idPedido ID del pedido.
     * @return Lista de detalles del pedido.
     * @throws NotFoundException si no se encuentran detalles asociados.
     */
    private List<DetallePedido> obtenerDetallePedidoById(Long idPedido) {
        var detallePedidos = repository.findByPedido_Id(idPedido);
        if (detallePedidos.isEmpty()) {
            throw new NotFoundException("No hay detalle del pedido por ese pedido");
        }
        return detallePedidos;
    }

    /**
     * Crea un nuevo detalle de pedido.
     * 
     * @param datos   Datos para registrar el detalle.
     * @param pedido  Pedido asociado.
     * @return DTO con el detalle creado.
     */
    public DatosDetalleDetallePedido crear(DatosRegistrarDetallePedido datos, Pedido pedido) {
        var producto = productoService.obtenerProductoPorId(datos.idProducto());
        var detalle = new DetallePedido(datos, pedido, producto);
        var nuevoDetalle = repository.save(detalle);
        return detalleDetallePedido(nuevoDetalle);
    }

    /**
     * Edita un detalle de pedido existente.
     * 
     * @param datos   Datos actualizados del detalle.
     * @param pedido  Pedido asociado.
     * @return Respuesta indicando el resultado de la operación.
     */
    public DatosDetalleResponse editar(DatosRegistrarDetallePedido datos, Pedido pedido) {
        var producto = productoService.obtenerProductoPorId(datos.idProducto());
        var detalleExistente = repository.findByPedidoIdAndProductoId(pedido.getId(), producto.getId())
                .orElseThrow(() -> new NotFoundException("Detalle no encontrado"));

        if (!detalleExistente.getProducto().equals(producto)) {
            repository.delete(detalleExistente);
            this.crear(datos, pedido);
            return new DatosDetalleResponse(200, "Producto del detalle cambiado correctamente");
        }

        if (!detalleExistente.getHumedad().equals(datos.humedad()))
            detalleExistente.setHumedad(datos.humedad());
        if (!detalleExistente.getFermentacion().equals(datos.fermentacion()))
            detalleExistente.setFermentacion(datos.fermentacion());
        if (!detalleExistente.getEstadoCacao().equals(datos.estadoCacao()))
            detalleExistente.setEstadoCacao(datos.estadoCacao());
        if (!detalleExistente.getCantidad().equals(datos.cantidad()))
            detalleExistente.setCantidad(datos.cantidad());
        if (!detalleExistente.getPeso().equals(datos.peso()))
            detalleExistente.setPeso(datos.peso());

        repository.save(detalleExistente);
        return new DatosDetalleResponse(200, "Detalle del pedido actualizado correctamente");
    }

    /**
     * Obtiene todos los detalles de un pedido específico.
     * 
     * @param idPedido ID del pedido.
     * @return Lista de detalles en formato DTO.
     */
    public List<DatosDetalleDetallePedido> obtenerPorPedidoId(Long idPedido) {
        var detalle = obtenerDetallePedidoById(idPedido);
        return detalle.stream().map(this::detalleDetallePedido).toList();
    }

    /**
     * Elimina todos los detalles asociados a un pedido.
     * 
     * @param idPedido ID del pedido.
     * @return Respuesta indicando el resultado de la operación.
     */
    public DatosDetalleResponse eliminarPorPedidoId(Long idPedido) {
        var detalle = obtenerDetallePedidoById(idPedido);
        repository.deleteAll(detalle);
        return new DatosDetalleResponse(200, "Detalle del pedido eliminado correctamente");
    }
}
