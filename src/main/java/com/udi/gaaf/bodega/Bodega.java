package com.udi.gaaf.bodega;

import java.util.List;

import com.udi.gaaf.inventario.Inventario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bodega")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bodega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bodega")
	Long id;
	String name;
	String ubicacion;
	@OneToMany(mappedBy = "bodega", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Inventario> inventarios;
}
