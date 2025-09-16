package com.udi.gaaf.proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	

	
	public List<DatosDetalleProveedor> findAll() {
		var proveedores = repository.findAll();
		return proveedores.stream()
				.map(p -> new DatosDetalleProveedor(p.nit, p.nombre, p.telefono, p.correo, p.direccion))
				.toList();
	}
	
}
