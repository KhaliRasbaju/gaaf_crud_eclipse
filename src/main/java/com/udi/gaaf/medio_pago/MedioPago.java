package com.udi.gaaf.medio_pago;

import com.udi.gaaf.metodo_pago.MetodoPago;
import com.udi.gaaf.pedido.Pedido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medio_pago")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class MedioPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String referencia;
	
	
	@OneToOne
	@JoinColumn(name ="id_metodo_pago", nullable = false)
	private MetodoPago metodo;
	
	
	@OneToOne(mappedBy = "pago")
	private Pedido pedido;
	
}
