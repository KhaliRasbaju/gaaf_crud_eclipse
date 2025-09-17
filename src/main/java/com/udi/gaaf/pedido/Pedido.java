package com.udi.gaaf.pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.udi.gaaf.detalle_pedido.DetallePedido;
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
	@Column(name = "fecha_pedido")
	LocalDateTime fechaPedido;
	@Column(name = "fecha_entrega")
	LocalDateTime fechaEntrega;
	Boolean recibido;
	
	Float valor;
	
	@ManyToOne()
	@JoinColumn(name = "nit_proveedor", nullable = false)
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePedido> detallePedidos;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransaccionInventario> transaccionInventarios;
	
	
	
}
