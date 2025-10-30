package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.detalle_pedido.DetallePedido;
import com.udi.gaaf.medio_pago.MedioPago;
import com.udi.gaaf.proveedor.Proveedor;
import com.udi.gaaf.transaccion_inventario.TransaccionInventario;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un pedido en el sistema.
 * Un pedido está asociado a un proveedor, medio de pago, detalles y posibles transacciones de inventario.
 */
@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    /** Identificador único del pedido. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    /** Fecha en la que se realizó el pedido. */
    @Column(name = "fecha_pedido", nullable = true)
    private LocalDateTime fechaPedido;

    /** Fecha en la que el pedido fue entregado. */
    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    /** Indica si el pedido ha sido recibido. */
    @Column(nullable = true)
    private Boolean recibido;

    /** Valor total del pedido. */
    private Double valor;

    /** Proveedor al que se realizó el pedido. */
    @ManyToOne
    @JoinColumn(name = "nit", nullable = false)
    private Proveedor proveedor;

    /** Detalles individuales del pedido (productos y cantidades). */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetallePedido> detallePedidos;

    /** Transacciones de inventario relacionadas con este pedido. */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TransaccionInventario> transaccionInventarios;

    /** Medio de pago asociado al pedido. */
    @OneToOne
    @JoinColumn(name = "id_medio_pago", nullable = false)
    private MedioPago pago;

    /**
     * Constructor para crear un pedido a partir de un DTO y entidades asociadas.
     *
     * @param datos      Datos del pedido a registrar.
     * @param proveedor  Proveedor asociado al pedido.
     * @param medio      Medio de pago utilizado.
     */
    public Pedido(DatosRegistrarPedido datos, Proveedor proveedor, MedioPago medio) {
        this.fechaPedido = datos.fechaPedido();
        this.recibido = false;
        this.valor = datos.valor();
        this.proveedor = proveedor;
        this.pago = medio;
        this.detallePedidos = new HashSet<>();
        this.transaccionInventarios = new HashSet<>();
    }
}
