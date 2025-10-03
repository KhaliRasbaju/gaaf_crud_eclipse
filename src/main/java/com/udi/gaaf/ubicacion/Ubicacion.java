package com.udi.gaaf.ubicacion;

import com.udi.gaaf.municipio.Municipio;
import com.udi.gaaf.proveedor.Proveedor;

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

@Entity
@Table(name = "ubicacion_proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "nit")
	private Proveedor proveedor;
	
	@OneToOne
	@JoinColumn(name = "id_ubicacion_municipio")
	private Municipio municipio;
}
