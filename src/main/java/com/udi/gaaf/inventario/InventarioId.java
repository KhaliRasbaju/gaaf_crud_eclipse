package com.udi.gaaf.inventario;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class InventarioId implements Serializable{
	@Column(name = "id_producto") 
	private Long idProducto;
	@Column(name = "id_bodega")
	private Long idBodega;
}
