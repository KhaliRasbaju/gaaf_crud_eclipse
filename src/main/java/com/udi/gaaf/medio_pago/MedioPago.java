package com.udi.gaaf.medio_pago;

import com.udi.gaaf.metodo_pago.MetodoPago;
import com.udi.gaaf.pedido.Pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un medio de pago registrado en el sistema.
 * Contiene la referencia del pago, su método y el pedido asociado.
 */
@Entity
@Table(name = "medio_pago")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedioPago {

    /** Identificador único del medio de pago */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medio_pago")
    private Long id;

    /** Referencia única del pago */
    @Column(name = "referencia_pago")
    private String referencia;

    /** Método de pago asociado (muchos medios pueden pertenecer a un método) */
    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodo;

    /** Pedido asociado al medio de pago (relación uno a uno) */
    @OneToOne(mappedBy = "pago")
    private Pedido pedido;

    /**
     * Constructor para crear un medio de pago a partir de los datos de registro.
     *
     * @param datos  Datos del medio de pago (referencia e id de método).
     * @param metodo Objeto del método de pago asociado.
     */
    public MedioPago(DatosRegistrarMedioPago datos, MetodoPago metodo) {
        this.referencia = datos.referencia();
        this.metodo = metodo;
    }
}