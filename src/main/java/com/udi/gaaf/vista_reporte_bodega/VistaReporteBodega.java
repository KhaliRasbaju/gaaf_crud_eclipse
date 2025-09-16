package com.udi.gaaf.vista_reporte_bodega;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vista_reporte_bodega")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class VistaReporteBodega {

	@Id
	Long id;
	String bodega;
	@Column(name = "cantidad_disponible_total")
	Integer cantidadDisponibleTotal;
	@Column(name = "cantidad_reservada_total")
	Integer cantidadReservadaTotal;
	Integer total;
}
