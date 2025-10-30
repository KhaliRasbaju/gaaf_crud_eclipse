package com.udi.gaaf.vista_pedidos_proveedor;

import java.time.LocalDateTime;

import com.udi.gaaf.common.VistaEstado;
import com.udi.gaaf.cuenta.TipoCuenta;

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
 * Entidad que representa la vista materializada de los pedidos realizados a proveedores.
 * Contiene información consolidada del pedido, proveedor y método de pago.
 * 
 * <p>Esta entidad se usa para generar reportes y visualizaciones
 * de los pedidos en estado actual sin necesidad de consultar múltiples tablas.</p>
 */
@Entity
@Table(name = "vista_pedidos_proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idPedido")
public class VistaPedidosProveedor {

	/** Identificador único del pedido. */
	@Id
	@Column(name = "id_pedido")
	private Long idPedido;

	/** Fecha en la que se realizó el pedido. */
	@Column(name = "fecha_pedido")
	private LocalDateTime fechaPedido;

	/** Fecha programada para la entrega del pedido. */
	@Column(name = "fecha_entrega")
	private LocalDateTime fechaEntrega;

	/** Estado actual del pedido (por ejemplo, PENDIENTE o RECIBIDO). */
	@Enumerated(EnumType.STRING)
	private VistaEstado estado;

	/** Nombre del proveedor asociado al pedido. */
	private String proveedor;

	/** Información de contacto del proveedor. */
	@Column(name = "contacto_proveedor")
	private String contactoProveedor;

	/** Referencia de pago asociada al pedido. */
	@Column(name = "referencia_pago")
	private String referenciaPago;

	/** Método de pago utilizado por el proveedor. */
	@Column(name = "metodo_pago")
	private String metodoPago;

	/** Número de cuenta del proveedor. */
	@Column(name = "numero_cuenta")
	private Integer nuemroCuenta;

	/** Tipo de cuenta bancaria del proveedor (por ejemplo, AHORROS o CORRIENTE). */
	@Column(name = "tipo_cuenta")
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipoCuenta;

	/** Nombre de la entidad bancaria del proveedor. */
	@Column(name = "entidad_bancaria")
	private String entidadBancaria;

	/** Valor total del pedido. */
	@Column(name = "valor_pedido")
	private Double valorPedido;
}
