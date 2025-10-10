package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.udi.gaaf.detalle_pedido.DetallePedido;
import com.udi.gaaf.medio_pago.MedioPago;
import com.udi.gaaf.proveedor.Proveedor;
import com.udi.gaaf.transaccion_inventario.TransaccionInventario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	Long id;
	@Column(name = "fecha_pedido", nullable =  true)
	LocalDateTime fechaPedido;
	@Column(name = "fecha_entrega")
	LocalDateTime fechaEntrega;
	@Column(nullable =  true)
	Boolean recibido;
	
	Double valor;
	
	@ManyToOne()
	@JoinColumn(name = "nit", nullable = false)
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DetallePedido> detallePedidos;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TransaccionInventario> transaccionInventarios;
	
	@OneToOne
	@JoinColumn(name ="id_medio_pago", nullable = false)
	private MedioPago pago;
	
	public Pedido(DatosRegistrarPedido datos, Proveedor proveedor, MedioPago medio) {
		this.fechaPedido = datos.fechaPedido();
		this.valor = datos.valor();
		this.proveedor = proveedor;
		this.pago = medio;
		this.detallePedidos = new HashSet<DetallePedido>();
		this.transaccionInventarios = new HashSet<TransaccionInventario>();
	}
}
