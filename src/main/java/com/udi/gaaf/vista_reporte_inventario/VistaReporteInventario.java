package com.udi.gaaf.vista_reporte_inventario;



import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vista_reporte_inventario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class VistaReporteInventario {
	@Id
	Long id;

	LocalDate fecha;
	@Column(name = "cantidad_disponible")
	Integer cantidadDisponible;
	@Column(name = "cantidad_reservada")
	Integer cantidadReservada;
	String producto;
	String bodega;
}
