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

@Entity
@Table(name = "vista_pedidos_proveedor")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idPedido")
public class VistaPedidosProveedor {

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
	
	@Column(name  = "referencia_pago")
	private String referenciaPago;
	
	@Column(name  = "metodo_pago")
	private String metodoPago;
	
	@Column(name  = "numero_cuenta")
	private Integer nuemroCuenta;
	
	@Column(name  = "tipo_cuenta")
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipoCuenta;
	
	@Column(name  = "entidad_bancaria")
	private String entidadBancaria;
	
	@Column(name  = "valor_pedido")
	private Double valorPedido;
}
