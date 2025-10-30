package com.udi.gaaf.municipio;

import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.departamento.Departamento;
import com.udi.gaaf.ubicacion.Ubicacion;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un municipio dentro del sistema.
 * Está asociado a un {@link Departamento} y puede tener varias {@link Ubicacion}.
 */
@Entity
@Table(name = "ubicacion_municipio")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Municipio {

    /** Identificador único del municipio. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_municipio")
    private Long id;

    /** Nombre del municipio. */
    private String nombre;

    /** Departamento al que pertenece el municipio. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion_departamento", nullable = false)
    private Departamento departamento;

    /** Conjunto de ubicaciones registradas dentro de este municipio. */
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private Set<Ubicacion> ubicaciones = new HashSet<>();
}
