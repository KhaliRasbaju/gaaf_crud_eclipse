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

@Entity
@Table(name = "vista_reporte_inventario_movimientos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "producto")
public class VistaInventarioMovimiento {

	@Id
	private String producto;
	
	private String bodega;
	
	@Enumerated(EnumType.STRING)
	private TipoTransaccion tipo;
	
	private Integer cantidad;
	
	private LocalDateTime fecha;
	
	private String observacion;
	
}
