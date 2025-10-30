package com.udi.gaaf.bodega;

import java.util.ArrayList;
import java.util.List;

import com.udi.gaaf.inventario.Inventario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * Entidad que representa una bodega dentro del sistema de gestión.
 * <p>
 * Una bodega contiene información básica como su nombre y ubicación,
 * además de mantener una relación con los inventarios almacenados en ella.
 * </p>
 * 
 * <p><b>Relaciones:</b></p>
 * <ul>
 *   <li>Una {@code Bodega} puede tener múltiples {@link Inventario} asociados.</li>
 * </ul>
 * 
 * @see Inventario
 */
@Entity
@Table(name = "bodega")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bodega {

    /** Identificador único de la bodega. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega")
    Long id;

    /** Nombre asignado a la bodega. */
    String nombre;

    /** Ubicación física de la bodega. */
    String ubicacion;

    /**
     * Lista de inventarios asociados a esta bodega.
     * <p>
     * La relación es de uno a muchos y se maneja en cascada, 
     * eliminando también los inventarios si la bodega es removida.
     * </p>
     */
    @OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventario> inventarios;

    /**
     * Constructor para registrar una nueva bodega a partir de los datos de entrada.
     * 
     * @param datos datos necesarios para crear una bodega, incluyendo nombre y ubicación
     */
    public Bodega(DatosRegistrarBodega datos) {
        this.nombre = datos.nombre();
        this.ubicacion = datos.ubicacion();
        this.inventarios = new ArrayList<>();
    }
}
