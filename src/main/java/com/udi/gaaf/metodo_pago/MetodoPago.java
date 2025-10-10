package com.udi.gaaf.metodo_pago;

import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.medio_pago.MedioPago;

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

@Entity
@Table(name = "metodo_pago")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetodoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_metodo_pago")
	private Long id;
	private String nombre;
	
	
	@OneToMany(mappedBy = "metodo")
	private Set<MedioPago> medio;
	
	
	public MetodoPago(DatosRegistrarCommon datos) {
		this.nombre = datos.nombre();
		this.medio = new HashSet<MedioPago>();
	}
	
}
