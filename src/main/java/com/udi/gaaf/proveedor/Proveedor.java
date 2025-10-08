package com.udi.gaaf.proveedor;



import java.util.HashSet;
import java.util.Set;


import com.udi.gaaf.cuenta.Cuenta;
import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.ubicacion.Ubicacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="proveedor")
@Getter
@Setter
@NoArgsConstructor

@EqualsAndHashCode(of ="nit")
public class Proveedor {
	
	@Id
	@Column(name = "nit")
	private Long nit;	
	private String nombre;
	private String telefono;
	private String correo;
	private Boolean activo;
	
	
	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;

	
	
	@OneToOne(mappedBy = "proveedor", orphanRemoval = true, fetch = FetchType.LAZY)
	private Ubicacion ubicaciones;
	
	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Cuenta> cuentas;
	
	
	public Proveedor(DatosRegistrarProveedor datos) {
		this.nit = datos.nit();
		this.activo = true;
		this.nombre = datos.nombre();
		this.telefono = datos.telefono();
		this.correo = datos.correo();
		this.pedidos = new HashSet<Pedido>();
		this.cuentas = new HashSet<Cuenta>();
	}
}
