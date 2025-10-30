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

/**
 * Entidad que representa la vista materializada de los reportes de compras realizadas.
 * 
 * <p>Contiene información consolidada de los pedidos de compra,
 * los proveedores asociados y los productos adquiridos.</p>
 */
@Entity
@Table(name = "vista_reporte_compras")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idPedido")
public class VistaCompra {
	
	/** Identificador único del pedido. */
	@Id
	@Column(name = "id_pedido")
	private Long idPedido;
	
	/** Fecha en la que se realizó el pedido de compra. */
	@Column(name = "fecha_pedido")
	private LocalDateTime fechaPedido;
	
	/** Fecha estimada de entrega del pedido. */
	@Column(name = "fecha_entrega")
	private LocalDateTime fechaEntrega;
	
	/** Estado actual del pedido (por ejemplo, PENDIENTE o RECIBIDO). */
	@Enumerated(EnumType.STRING)
	private VistaEstado estado;
	
	/** Nombre del proveedor del pedido. */
	private String proveedor;
	
	/** Información de contacto del proveedor. */
	@Column(name = "contacto_proveedor")
	private String contactoProveedor;
	
	/** Teléfono del proveedor asociado al pedido. */
	@Column(name = "telefono_proveedor")
	private String telefonoProveedor;
	
	/** Nombre del producto adquirido en la compra. */
	private String producto;
	
	/** Cantidad del producto solicitado. */
	private String cantidad;
	
	/** Peso total del pedido (si aplica). */
	private Float peso;
	
	/** Valor total del pedido de compra. */
	@Column(name = "valor_pedido")
	private Double valorPedido;
}
