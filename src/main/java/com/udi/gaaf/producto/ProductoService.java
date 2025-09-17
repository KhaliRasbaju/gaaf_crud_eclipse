package com.udi.gaaf.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository repository;
	
	
	public List<DatosDetalleProducto> findAll(){
		var productos = repository.findAll();
		return productos.stream()
				.map(pr -> new DatosDetalleProducto(pr.id, pr.nombre, pr.descripcion))
				.toList();
	}
	
	public List<DatosDetalleProducto> findAllByIds(List<Long> productosIds){
		var productos = repository.findAllById(productosIds);
		return productos.stream()
				.map(pr -> new DatosDetalleProducto(pr.id, pr.nombre, pr.descripcion))
				.toList();
	}
}
