package com.udi.gaaf.transaccion_inventario;

import java.time.LocalDateTime;

import com.udi.gaaf.inventario.Inventario;
import com.udi.gaaf.pedido.Pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una transacción de inventario.
 * Se utiliza para registrar cualquier movimiento de productos (entrada, salida o merma).
 */
@Entity
@Table(name = "transaccion_inventario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransaccionInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion_inventario")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;

    private Integer cantidad;

    private LocalDateTime fecha;

    private String observacion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_pedido", nullable = true)
    private Pedido pedido;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto"),
        @JoinColumn(name = "id_bodega", referencedColumnName = "id_bodega")
    })
    private Inventario inventario;

    /**
     * Constructor que inicializa una transacción con los datos proporcionados.
     *
     * @param datos      Datos del registro de la transacción.
     * @param inventario Inventario relacionado.
     * @param pedido     Pedido asociado (puede ser null).
     */
    public TransaccionInventario(DatosRegistrarTransaccion datos, Inventario inventario, Pedido pedido) {
        this.tipo = datos.tipo();
        this.cantidad = datos.cantidad();
        this.fecha = LocalDateTime.now();
        this.observacion = datos.observacion();
        this.inventario = inventario;

        if (pedido != null) this.pedido = pedido;
    }
}