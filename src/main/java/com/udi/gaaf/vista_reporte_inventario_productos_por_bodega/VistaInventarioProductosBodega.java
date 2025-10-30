package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la vista materializada del reporte de inventario de productos por bodega.
 * 
 * <p>Esta vista muestra el inventario actualizado de cada producto disponible
 * en una bodega específica, incluyendo la cantidad y la fecha del último movimiento.</p>
 */
@Entity
@Table(name = "vista_reporte_inventario_productos_por_bodega")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "bodega")
public class VistaInventarioProductosBodega {

	/** Nombre de la bodega en la que se encuentra el producto. */
	@Id
	private String bodega;

	/** Nombre o descripción del producto almacenado. */
	private String producto;

	/** Cantidad actual del producto en la bodega. */
	private Integer cantidad;

	/** Fecha y hora del último registro o actualización del producto en el inventario. */
	private LocalDateTime fecha;
}
