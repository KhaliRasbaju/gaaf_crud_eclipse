package com.udi.gaaf.municipio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.errors.NotFoundException;

@Service
public class MunicipioService {

	@Autowired
	private MunicipioRepository repository;
	
	private DatosDetalleMunicipio detalleMunicipio(Municipio municipio) {
		return new DatosDetalleMunicipio(municipio.getId(), municipio.getNombre(), municipio.getDepartamento().getNombre());
	}
	
	
	public Municipio obtenerMunicipioPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Municipio no encontrado por el id" + id));
	}
	
	
	
	public DatosDetalleMunicipio obtenerPorId(Long id) {
		var municipio = obtenerMunicipioPorId(id);
		return detalleMunicipio(municipio);
	}
	
	
	public List<DatosDetalleMunicipio> obtenerTodos(Long id) {
		var municipios = repository.findAllByDepartamento_Id(id);
		return municipios.stream().map(m -> detalleMunicipio(m)).toList();
	}
	
	
	
	
}
