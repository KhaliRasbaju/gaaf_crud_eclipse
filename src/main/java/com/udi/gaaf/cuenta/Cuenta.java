package com.udi.gaaf.cuenta;

import com.udi.gaaf.entidad_bancaria.EntidadBancaria;
import com.udi.gaaf.proveedor.Proveedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "cuenta_proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta_proveedor")
	private Long id;
	
	
	@Column(name = "numero_cuenta")
	private String numero;
	
	
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipo;
	
	
	@ManyToOne
	@JoinColumn(name = "nit", nullable = false)
	private Proveedor proveedor;
	
	@ManyToOne
	@JoinColumn(name ="id_entidad_bancaria",nullable = false)
	private EntidadBancaria entidad;
	
	
	
	
	public Cuenta(DatosRegistrarCuenta datos, EntidadBancaria entidad, Proveedor proveedor) {
		this.numero = datos.numero();
		this.tipo = datos.tipo();
		this.proveedor = proveedor;
		this.entidad = entidad;
	}
	
	
	

}
