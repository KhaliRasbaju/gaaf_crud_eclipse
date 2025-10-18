package com.udi.gaaf.detalle_pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {
	List<DetallePedido> findByPedido_Id(Long idPedido);
	
	Optional<DetallePedido> findByPedidoIdAndProductoId(Long idPedio, Long idProducto);

}
