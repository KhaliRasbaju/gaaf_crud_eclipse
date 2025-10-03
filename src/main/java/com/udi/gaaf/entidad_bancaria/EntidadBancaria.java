package com.udi.gaaf.entidad_bancaria;

import com.udi.gaaf.cuenta.Cuenta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	private Long id;
	
	private String nombre;
	
	
	@OneToOne(mappedBy = "entidad")
	private Cuenta cuenta;
	
}
