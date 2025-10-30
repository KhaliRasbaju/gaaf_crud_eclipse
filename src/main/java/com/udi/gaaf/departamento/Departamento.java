package com.udi.gaaf.departamento;

import java.util.HashSet;
import java.util.Set;
import com.udi.gaaf.municipio.Municipio;
import jakarta.persistence.CascadeType;
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
 * Entidad que representa un departamento dentro del sistema de ubicación.
 * <p>
 * Cada departamento puede contener múltiples municipios asociados.
 * </p>
 * 
 * <p>La relación con {@link Municipio} es de tipo <b>uno a muchos</b>, lo que
 * significa que un departamento puede tener varios municipios registrados.</p>
 */
@Entity
@Table(name = "ubicacion_departamento")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Departamento {

    /** Identificador único del departamento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_departamento")
    private Long id;

    /** Nombre del departamento. */
    private String nombre;

    /**
     * Conjunto de municipios asociados al departamento.
     * <p>
     * Configuración:
     * <ul>
     *   <li><b>mappedBy:</b> indica que la relación se gestiona desde la entidad {@link Municipio}.</li>
     *   <li><b>cascade:</b> todas las operaciones en cascada están habilitadas.</li>
     *   <li><b>orphanRemoval:</b> elimina los municipios huérfanos al eliminar su relación con el departamento.</li>
     *   <li><b>fetch:</b> tipo LAZY, los municipios se cargan solo cuando son requeridos.</li>
     * </ul>
     * </p>
     */
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Municipio> municipios = new HashSet<Municipio>();
}
