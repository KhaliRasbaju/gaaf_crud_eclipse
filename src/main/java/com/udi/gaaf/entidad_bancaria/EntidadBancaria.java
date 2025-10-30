package com.udi.gaaf.entidad_bancaria;

import java.util.ArrayList;
import java.util.List;

import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.cuenta.Cuenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa la entidad bancaria registrada en el sistema.
 * Contiene información básica como el nombre de la entidad
 * y las cuentas asociadas a ella.
 */
@Entity
@Table(name = "entidad_bancaria")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EntidadBancaria {

    /** Identificador único de la entidad bancaria. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad_bancaria")
    private Long id;

    /** Nombre de la entidad bancaria. */
    private String nombre;

    /** Lista de cuentas asociadas a la entidad bancaria. */
    @OneToMany(mappedBy = "entidad", fetch = FetchType.LAZY)
    private List<Cuenta> cuentas;

    /**
     * Constructor que crea una nueva instancia de {@link EntidadBancaria}
     * a partir de los datos proporcionados.
     *
     * @param datos objeto que contiene la información necesaria para registrar la entidad bancaria.
     */
    public EntidadBancaria(DatosRegistrarCommon datos) {
        this.nombre = datos.nombre();
        this.cuentas = new ArrayList<Cuenta>();
    }
}
