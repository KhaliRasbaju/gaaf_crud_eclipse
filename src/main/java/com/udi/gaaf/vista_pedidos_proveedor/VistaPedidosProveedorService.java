package com.udi.gaaf.vista_pedidos_proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistaPedidosProveedorService {

	@Autowired
	private VistaPedidosProveedorRepository repository;
	
	
	public List<VistaPedidosProveedor> obtenerReporte(){
		return repository.findAll();
	}
	
}
