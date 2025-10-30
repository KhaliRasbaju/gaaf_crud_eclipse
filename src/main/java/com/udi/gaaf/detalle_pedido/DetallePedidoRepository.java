package com.udi.gaaf.detalle_pedido;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de los detalles de pedidos.
 * Proporciona métodos para consultar detalles según pedido o producto.
 */
@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {

    /**
     * Obtiene todos los detalles asociados a un pedido.
     * 
     * @param idPedido ID del pedido.
     * @return Lista de detalles del pedido.
     */
    List<DetallePedido> findByPedido_Id(Long idPedido);

    /**
     * Busca un detalle específico de pedido según pedido y producto.
     * 
     * @param idPedido   ID del pedido.
     * @param idProducto ID del producto.
     * @return Detalle del pedido, si existe.
     */
    Optional<DetallePedido> findByPedidoIdAndProductoId(Long idPedido, Long idProducto);
}
