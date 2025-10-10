package com.udi.gaaf.vista_reporte_inventario_productos_por_bodega;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vista_reporte_inventario_productos_por_bodega")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "bodega")
public class VistaInventarioProductosBodega {

	@Id
	private String bodega;
	
	private String producto;
	
	private Integer cantidad;
	
	private LocalDateTime fecha;
}
