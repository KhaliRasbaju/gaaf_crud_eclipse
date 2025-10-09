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

@Entity
@Table(name = "entidad_bancaria")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EntidadBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entidad_bancaria")
	private Long id;
	
	private String nombre;
	
	
	@OneToMany(mappedBy = "entidad",fetch = FetchType.LAZY)
	private List<Cuenta> cuentas;
	
	
	public EntidadBancaria(DatosRegistrarCommon datos) {
		this.nombre = datos.nombre();
		this.cuentas = new ArrayList<Cuenta>();
	}
}
