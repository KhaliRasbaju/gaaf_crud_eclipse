package com.udi.gaaf.detalle_pedido;

import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.producto.Producto;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa el detalle de un pedido.
 * Contiene información sobre las características del producto solicitado.
 */
@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetallePedido {

    /** Clave compuesta que relaciona pedido y producto */
    @EmbeddedId
    private DetallePedidoId id;

    /** Nivel de humedad del producto */
    private Float humedad;

    /** Estado del cacao (porcentaje) */
    @Column(name = "estado_cacao")
    private Float estadoCacao;

    /** Nivel de fermentación (porcentaje) */
    private Float fermentacion;

    /** Cantidad solicitada del producto */
    private Integer cantidad;

    /** Peso total del producto */
    private Float peso;

    /** Pedido al que pertenece el detalle */
    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    /** Producto asociado al detalle */
    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    /**
     * Constructor que crea una instancia de DetallePedido a partir de los datos registrados.
     * 
     * @param datos     Datos de registro del detalle.
     * @param pedido    Pedido asociado.
     * @param producto  Producto asociado.
     */
    public DetallePedido(DatosRegistrarDetallePedido datos, Pedido pedido, Producto producto) {
        this.id = new DetallePedidoId(pedido.getId(), producto.getId());
        this.humedad = datos.humedad();
        this.estadoCacao = datos.estadoCacao();
        this.fermentacion = datos.fermentacion();
        this.cantidad = datos.cantidad();
        this.peso = datos.peso();
        this.pedido = pedido;
        this.producto = producto;
    }
}
