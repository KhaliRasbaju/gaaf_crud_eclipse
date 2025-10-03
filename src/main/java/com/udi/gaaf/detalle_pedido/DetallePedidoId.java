package com.udi.gaaf.detalle_pedido;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DetallePedidoId implements Serializable {
	@Column(name ="id_pedido")
	private Long idPedido;
	@Column(name = "id_producto")
	private Long idProducto;
}
