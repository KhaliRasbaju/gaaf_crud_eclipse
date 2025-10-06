package com.udi.gaaf.entidad_bancaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotFoundException;

@Service
public class EntidadBancariaService {

	@Autowired
	public EntidadBancariaRepository repository;
	
	private DatosDetalleEntidadBancaria detalleEntidad(EntidadBancaria entidad) {
		return new DatosDetalleEntidadBancaria(entidad.getId(), entidad.getNombre());
	}
	
	private EntidadBancaria obtenerEntidadBancariaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Entidad bancaria no encontrada"));
	}
	
	public DatosDetalleEntidadBancaria crear(DatosRegistrarEntidadBancaria datos) {
		var entidad = new EntidadBancaria(datos);
		var nuevaEntidad = repository.save(entidad);
		return detalleEntidad(nuevaEntidad);
	}
	
	
	public DatosDetalleEntidadBancaria editar(Long id, DatosRegistrarEntidadBancaria datos) {
		var entidad  = obtenerEntidadBancariaPorId(id);
		if(datos.nombre() != null) entidad.setNombre(datos.nombre());
		repository.save(entidad);
		return detalleEntidad(entidad);
	}
	
	public DatosDetalleEntidadBancaria obtenerPorId(Long id) {
		var entidad = obtenerEntidadBancariaPorId(id);
		return detalleEntidad(entidad);
	}
	
	public List<DatosDetalleEntidadBancaria> obtenerTodos(){
		var entidades = repository.findAll();
		return entidades.stream().map(en -> detalleEntidad(en)).toList();
	}
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var entidad = obtenerEntidadBancariaPorId(id);
		repository.delete(entidad);
		return new DatosDetalleResponse(200, "Entidad Bancaria eliminada correctamente");
	}
	
}
