package com.udi.gaaf.proveedor;


import java.util.ArrayList;
import java.util.List;

import com.udi.gaaf.cuenta.Cuenta;
import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.ubicacion.Ubicacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	Long nit;	
	String direccion;
	String nombre;
	String telefono;
	String correo;
	
	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidos;

	
	@OneToMany(mappedBy = "proveedor", orphanRemoval = true)
	private List<Ubicacion> ubicaciones;
	
	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cuenta> cuentas;
	
	
	public Proveedor(DatosRegistrarProveedor datos) {
		this.nit = datos.nit();
		this.direccion = datos.direccion();
		this.nombre = datos.nombre();
		this.telefono = datos.telefono();
		this.correo = datos.correo();
		this.pedidos = new ArrayList<Pedido>();
		this.ubicaciones = new ArrayList<Ubicacion>();
		this.cuentas = new ArrayList<Cuenta>();
	}
}
