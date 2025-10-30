package com.udi.gaaf.proveedor;

import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.cuenta.Cuenta;
import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.ubicacion.Ubicacion;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad JPA que representa un proveedor dentro del sistema.
 * Un proveedor puede tener múltiples pedidos, cuentas y una ubicación asociada.
 */
@Entity
@Table(name = "proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "nit")
public class Proveedor {

    /** Identificador único del proveedor (NIT). */
    @Id
    @Column(name = "nit")
    private Long nit;

    /** Nombre del proveedor. */
    private String nombre;

    /** Teléfono de contacto del proveedor. */
    private String telefono;

    /** Correo electrónico del proveedor. */
    private String correo;

    /** Indica si el proveedor está activo o inactivo. */
    private Boolean activo;

    /** Pedidos asociados a este proveedor. */
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    /** Ubicación asociada al proveedor. */
    @OneToOne(mappedBy = "proveedor", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Ubicacion ubicaciones;

    /** Cuentas asociadas al proveedor. */
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Cuenta> cuentas;

    /**
     * Constructor que inicializa un proveedor a partir de un DTO.
     *
     * @param datos Datos del proveedor a registrar.
     */
    public Proveedor(DatosRegistrarProveedor datos) {
        this.nit = datos.nit();
        this.activo = true;
        this.nombre = datos.nombre();
        this.telefono = datos.telefono();
        this.correo = datos.correo();
        this.pedidos = new HashSet<>();
        this.cuentas = new HashSet<>();
    }
}
