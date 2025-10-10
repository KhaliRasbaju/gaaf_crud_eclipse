package com.udi.gaaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.departamento.DepartamentoService;
import com.udi.gaaf.municipio.DatosDetalleMunicipio;
import com.udi.gaaf.municipio.MunicipioService;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

	@Autowired
	private MunicipioService municipioService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	
	@GetMapping("/departamento/{id}")
	public ResponseEntity<DatosDetalleCommon> obtenerDepartamentoPorId(@PathVariable Long id){
		var detalle = departamentoService.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/departamento")
	public ResponseEntity<List<DatosDetalleCommon>> obtenerDepartamentos(){
		var detalle = departamentoService.obtenerTodos();
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/municipio/{id}")
	public ResponseEntity<DatosDetalleMunicipio> obtenerMunicipioPorId(@PathVariable Long id){
		var detalle = municipioService.obtenerPorId(id);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping("/municipio-departamento/{id}")
	public ResponseEntity<List<DatosDetalleMunicipio>> obteneMunicipiosPorIdDepartamento(@PathVariable Long id){
		var detalle = municipioService.obtenerTodos(id);
		return ResponseEntity.ok(detalle);
	}
}
