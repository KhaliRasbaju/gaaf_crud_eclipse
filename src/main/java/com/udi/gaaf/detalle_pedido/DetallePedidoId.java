package com.udi.gaaf.detalle_pedido;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa la clave compuesta de la entidad {@link DetallePedido}.
 * Combina el identificador del pedido con el identificador del producto.
 */
@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DetallePedidoId implements Serializable {

    /** Identificador del pedido */
    @Column(name = "id_pedido")
    private Long idPedido;

    /** Identificador del producto */
    @Column(name = "id_producto")
    private Long idProducto;

    /**
     * Constructor para inicializar ambos identificadores.
     * 
     * @param idPedido   ID del pedido.
     * @param idProducto ID del producto.
     */
    public DetallePedidoId(Long idPedido, Long idProducto) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
    }
}
