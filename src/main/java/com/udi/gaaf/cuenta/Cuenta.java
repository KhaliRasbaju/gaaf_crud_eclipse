package com.udi.gaaf.cuenta;

import com.udi.gaaf.entidad_bancaria.EntidadBancaria;
import com.udi.gaaf.proveedor.Proveedor;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa una cuenta bancaria asociada a un proveedor dentro del sistema.
 * <p>
 * Cada cuenta contiene información como el número de cuenta, tipo de cuenta,
 * la entidad bancaria a la que pertenece y el proveedor propietario.
 * </p>
 *
 * <p>
 * La entidad se almacena en la tabla {@code cuenta_proveedor}.
 * </p>
 */
@Entity
@Table(name = "cuenta_proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cuenta {

    /** Identificador único de la cuenta bancaria. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta_proveedor")
    private Long id;

    /** Número de la cuenta bancaria. */
    @Column(name = "numero_cuenta")
    private String numero;

    /** Tipo de cuenta (AHORROS, CORRIENTE, etc.). */
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

    /** Proveedor asociado a la cuenta bancaria. */
    @ManyToOne
    @JoinColumn(name = "nit", nullable = false)
    private Proveedor proveedor;

    /** Entidad bancaria a la que pertenece la cuenta. */
    @ManyToOne
    @JoinColumn(name = "id_entidad_bancaria", nullable = false)
    private EntidadBancaria entidad;

    /**
     * Constructor utilizado para crear una nueva cuenta a partir de los datos registrados.
     *
     * @param datos objeto con la información básica de la cuenta
     * @param entidad entidad bancaria asociada
     * @param proveedor proveedor propietario de la cuenta
     */
    public Cuenta(DatosRegistrarCuenta datos, EntidadBancaria entidad, Proveedor proveedor) {
        this.numero = datos.numero();
        this.tipo = datos.tipo();
        this.proveedor = proveedor;
        this.entidad = entidad;
    }
}
