package com.udi.gaaf.proveedor;


import java.util.List;

import com.udi.gaaf.pedido.Pedido;

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

}
