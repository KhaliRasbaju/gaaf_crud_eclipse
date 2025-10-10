package com.udi.gaaf.departamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.errors.NotFoundException;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository repository;
	
	
	
	private DatosDetalleCommon detalleDepartamento(Departamento departamento) {
		return new DatosDetalleCommon(departamento.getId(), departamento.getNombre());
	}
	
	public Departamento obtenerDepartamentoPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Departamento no encontrado por el id: "+ id));
	}
	
	public DatosDetalleCommon obtenerPorId(Long id) {
		var departamento = obtenerDepartamentoPorId(id);
		return detalleDepartamento(departamento);	
	}
	
	public List<DatosDetalleCommon> obtenerTodos(){
		var departamentos  = repository.findAll();
		return departamentos.stream().map(d-> detalleDepartamento(d)).toList();
	}
	
	
	
}
