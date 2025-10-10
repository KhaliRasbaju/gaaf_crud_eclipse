package com.udi.gaaf.inventario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.udi.gaaf.bodega.Bodega;
import com.udi.gaaf.producto.Producto;
import com.udi.gaaf.transaccion_inventario.TransaccionInventario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor

@EqualsAndHashCode(of = "id")
public class Inventario {
	
	@EmbeddedId
	private InventarioId id;
	
	
	@Column(name = "fecha_actualizacion")
	private LocalDateTime fecha;
	
	@Column(name = "cantidad_disponible")
	private Integer cantidad;
	

	@OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TransaccionInventario> transaccionInventarios;
	
	
	@ManyToOne
	@MapsId("idBodega")
	@JoinColumn(name = "id_bodega", nullable = false)
	private Bodega bodega;
	
	
	@ManyToOne
	@MapsId("idProducto")
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;
	
	public Inventario (DatosRegistrarInventario datos, Bodega bodega, Producto producto) {
		this.id = new InventarioId(producto.getId(), bodega.getId());
		this.fecha = datos.fecha();
		this.cantidad = datos.cantidad();
		this.transaccionInventarios = new HashSet<TransaccionInventario>();
		this.bodega = bodega;
		this.producto = producto;
	}
	

}
