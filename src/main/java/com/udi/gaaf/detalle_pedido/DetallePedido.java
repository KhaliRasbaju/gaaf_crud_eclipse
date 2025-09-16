package com.udi.gaaf.detalle_pedido;

import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.producto.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetallePedido {
	@Id
	@Column(name = "id_detalle_pedido")
	Long id;
	Float humedad;
	@Column(name = "estado_cacao")
	Float estadoCacao;
	Float fermentacion;
	Integer cantidad;
	Float peso;
	
	
	@ManyToOne()
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido pedido;
	
	
	@ManyToOne()
	@JoinColumn(name = "id_inventario", nullable = false )
	private Producto producto;
}
