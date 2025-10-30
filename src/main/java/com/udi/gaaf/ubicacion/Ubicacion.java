package com.udi.gaaf.ubicacion;

import com.udi.gaaf.municipio.Municipio;
import com.udi.gaaf.proveedor.Proveedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * Entidad que representa la ubicación física de un proveedor.
 * Está relacionada con un municipio y un proveedor específico.
 */
@Entity
@Table(name = "ubicacion_proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ubicacion_proveedor")
	private Long id;
	
	private String direccion;
	
	@OneToOne
	@JoinColumn(name = "nit")
	private Proveedor proveedor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio", nullable = false)
	private Municipio municipio;

	/**
	 * Constructor que inicializa una ubicación con los datos proporcionados.
	 *
	 * @param datos      Datos necesarios para registrar la ubicación.
	 * @param municipio  Municipio al que pertenece la ubicación.
	 * @param proveedor  Proveedor asociado a la ubicación.
	 */
	public Ubicacion(DatosRegistrarUbicacion datos, Municipio municipio, Proveedor proveedor) {
		this.direccion = datos.direccion();
		this.municipio = municipio;
		this.proveedor = proveedor;	
	}
}
