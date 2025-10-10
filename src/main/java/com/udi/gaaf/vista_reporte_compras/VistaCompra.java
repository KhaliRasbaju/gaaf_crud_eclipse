package com.udi.gaaf.vista_reporte_compras;

import java.time.LocalDateTime;

import com.udi.gaaf.common.VistaEstado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vista_reporte_compras")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idPedido")
public class VistaCompra {
	

	@Id
	@Column(name = "id_pedido")
	private Long idPedido;
	
	@Column(name = "fecha_pedido")
	private LocalDateTime fechaPedido;
	
	
	@Column(name = "fecha_entrega")
	private LocalDateTime fechaEntrega;
	
	@Enumerated(EnumType.STRING)
	private VistaEstado estado;
	
	private String proveedor;
	
	@Column(name  = "contacto_proveedor")
	private String contactoProveedor;
	
	@Column(name = "telefono_proveedor")
	private String telefonoProveedor;
	
	private String producto;
	
	private String cantidad;
	
	private Float peso;
	
	@Column(name = "valor_pedido")
	private Double valorPedido;
}
