package com.udi.gaaf.inventario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.bodega.Bodega;
import com.udi.gaaf.producto.Producto;
import com.udi.gaaf.transaccion_inventario.TransaccionInventario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa el inventario en el sistema.
 * Cada inventario está asociado a una bodega y un producto específicos.
 */
@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Inventario {

    /** Identificador compuesto del inventario (bodega + producto). */
    @EmbeddedId
    private InventarioId id;

    /** Fecha de la última actualización del inventario. */
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fecha;

    /** Cantidad disponible en el inventario. */
    @Column(name = "cantidad_disponible")
    private Integer cantidad;

    /** Transacciones asociadas a este inventario. */
    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TransaccionInventario> transaccionInventarios;

    /** Bodega asociada al inventario. */
    @ManyToOne
    @MapsId("idBodega")
    @JoinColumn(name = "id_bodega", nullable = false)
    private Bodega bodega;

    /** Producto asociado al inventario. */
    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    /**
     * Constructor para crear una instancia de inventario.
     *
     * @param datos datos del registro del inventario.
     * @param bodega bodega asociada.
     * @param producto producto asociado.
     */
    public Inventario(DatosRegistrarInventario datos, Bodega bodega, Producto producto) {
        this.id = new InventarioId(producto.getId(), bodega.getId());
        this.fecha = datos.fecha();
        this.cantidad = datos.cantidad();
        this.transaccionInventarios = new HashSet<>();
        this.bodega = bodega;
        this.producto = producto;
    }
}
