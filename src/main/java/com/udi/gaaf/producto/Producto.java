package com.udi.gaaf.producto;

import java.util.ArrayList;
import java.util.List;

import com.udi.gaaf.detalle_pedido.DetallePedido;
import com.udi.gaaf.inventario.Inventario;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un producto dentro del sistema.
 * Un producto puede estar asociado a múltiples detalles de pedido y registros de inventario.
 */
@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {

    /** Identificador único del producto. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    /** Nombre del producto. */
    private String nombre;

    /** Descripción detallada del producto. */
    private String descripcion;

    /** Tipo de producto (CACAO o INGREDIENTES_COMPLEMENTARIOS). */
    @Enumerated(EnumType.STRING)
    private TipoProducto tipo;

    /** Detalles de pedido asociados a este producto. */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedidos;

    /** Registros de inventario relacionados con el producto. */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventario> inventarios;

    /**
     * Constructor que inicializa un producto a partir de un DTO.
     *
     * @param datos Datos del producto a registrar.
     */
    public Producto(DatosRegistrarProducto datos) {
        this.nombre = datos.nombre();
        this.descripcion = datos.descripcion();
        this.tipo = datos.tipo();
        this.detallePedidos = new ArrayList<>();
        this.inventarios = new ArrayList<>();
    }
}
