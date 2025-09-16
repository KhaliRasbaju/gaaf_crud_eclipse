package com.udi.gaaf.transaccion_inventario;

import java.time.LocalDateTime;

import com.udi.gaaf.inventario.Inventario;
import com.udi.gaaf.pedido.Pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of  = "id_transaccion_inventario")
public class TransaccionInventario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaccion_inventario")
	Long id;
	
	TransaccionTipo tipo;
	
	Integer cantidad;
	
	LocalDateTime fecha;
	
	String motivo;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = true)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_inventario", nullable = false)
	private Inventario inventario;
}
