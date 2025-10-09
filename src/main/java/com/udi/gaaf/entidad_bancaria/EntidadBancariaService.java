package com.udi.gaaf.entidad_bancaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.errors.NotFoundException;

@Service
public class EntidadBancariaService {

	@Autowired
	public EntidadBancariaRepository repository;
	
	private DatosDetalleCommon detalleEntidad(EntidadBancaria entidad) {
		return new DatosDetalleCommon(entidad.getId(), entidad.getNombre());
	}
	
	public EntidadBancaria obtenerEntidadBancariaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Entidad bancaria no encontrada"));
	}
	
	public DatosDetalleCommon crear(DatosRegistrarCommon datos) {
		var entidad = new EntidadBancaria(datos);
		var nuevaEntidad = repository.save(entidad);
		return detalleEntidad(nuevaEntidad);
	}
	
	
	public DatosDetalleCommon editar(Long id, DatosRegistrarCommon datos) {
		var entidad  = obtenerEntidadBancariaPorId(id);
		if(datos.nombre() != null) entidad.setNombre(datos.nombre());
		repository.save(entidad);
		return detalleEntidad(entidad);
	}
	
	public DatosDetalleCommon obtenerPorId(Long id) {
		var entidad = obtenerEntidadBancariaPorId(id);
		return detalleEntidad(entidad);
	}
	
	public List<DatosDetalleCommon> obtenerTodos(){
		var entidades = repository.findAll();
		return entidades.stream().map(en -> detalleEntidad(en)).toList();
	}
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var entidad = obtenerEntidadBancariaPorId(id);
		repository.delete(entidad);
		return new DatosDetalleResponse(200, "Entidad Bancaria eliminada correctamente");
	}
	
}
