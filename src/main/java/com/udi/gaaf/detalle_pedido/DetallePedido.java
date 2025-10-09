package com.udi.gaaf.detalle_pedido;

import com.udi.gaaf.pedido.Pedido;
import com.udi.gaaf.producto.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
	@EmbeddedId
	private DetallePedidoId id;
	Float humedad;
	@Column(name = "estado_cacao")
	Float estadoCacao;
	Float fermentacion;
	Integer cantidad;
	Float peso;
	
	
	@ManyToOne()
	@MapsId("idPedido")
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido pedido;
	
	
	@ManyToOne()
	@MapsId("idProducto")
	@JoinColumn(name = "id_producto", nullable = false )
	private Producto producto;
	
	
	public DetallePedido(DatosRegistrarDetallePedido datos, Pedido pedido, Producto producto) {
		this.humedad = datos.humedad();
		this.fermentacion = datos.fermentacion();
		this.cantidad = datos.cantidad();
		this.peso = datos.peso();
		this.pedido = pedido;
		this.producto = producto;
	}
}
