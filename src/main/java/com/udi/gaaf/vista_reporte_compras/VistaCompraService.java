package com.udi.gaaf.vista_reporte_compras;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VistaCompraService {

	@Autowired
	private VistaCompraRepository repository;
	
	public List<VistaCompra> obtenerReporte(){
		return repository.findAll();
	}
}

