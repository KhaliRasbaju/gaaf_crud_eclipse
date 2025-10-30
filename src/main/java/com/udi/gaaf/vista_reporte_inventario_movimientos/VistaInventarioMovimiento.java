package com.udi.gaaf.vista_reporte_inventario_movimientos;

import java.time.LocalDateTime;

import com.udi.gaaf.transaccion_inventario.TipoTransaccion;

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
 * Entidad que representa la vista materializada del reporte de movimientos de inventario.
 * 
 * <p>Esta vista consolida la información de entradas, salidas y ajustes 
 * realizados sobre los productos en las distintas bodegas, 
 * incluyendo tipo de transacción, cantidad y observaciones.</p>
 */
@Entity
@Table(name = "vista_reporte_inventario_movimientos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "producto")
public class VistaInventarioMovimiento {

	/** Nombre o identificador del producto afectado por la transacción. */
	@Id
	private String producto;

	/** Nombre de la bodega donde se realizó el movimiento. */
	private String bodega;

	/** Tipo de transacción de inventario (por ejemplo: ENTRADA, SALIDA, AJUSTE). */
	@Enumerated(EnumType.STRING)
	private TipoTransaccion tipo;

	/** Cantidad de unidades involucradas en la transacción. */
	private Integer cantidad;

	/** Fecha y hora en que se registró el movimiento. */
	private LocalDateTime fecha;

	/** Observaciones adicionales o comentarios sobre el movimiento. */
	private String observacion;
}
