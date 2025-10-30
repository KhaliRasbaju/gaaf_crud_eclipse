package com.udi.gaaf.inventario;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa la clave compuesta para la entidad {@link Inventario}.
 * Combina los identificadores de bodega y producto.
 */
@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class InventarioId implements Serializable {

    /** Identificador del producto. */
    @Column(name = "id_producto")
    private Long idProducto;

    /** Identificador de la bodega. */
    @Column(name = "id_bodega")
    private Long idBodega;

    /**
     * Constructor con parámetros para la clave compuesta.
     *
     * @param idProducto identificador del producto.
     * @param idBodega identificador de la bodega.
     */
    public InventarioId(Long idProducto, Long idBodega) {
        this.idProducto = idProducto;
        this.idBodega = idBodega;
    }
}
