package com.udi.gaaf.municipio;

import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.departamento.Departamento;
import com.udi.gaaf.ubicacion.Ubicacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ubicacion_municipio")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Municipio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ubicacion_municipio")
	private Long id;
	
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ubicacion_departamento", nullable = false)
	private Departamento departamento;
	
	
	@OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
	private Set<Ubicacion> ubicaciones = new HashSet<Ubicacion>();

}
