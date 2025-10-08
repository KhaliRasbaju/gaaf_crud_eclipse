package com.udi.gaaf.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.municipio.MunicipioService;
import com.udi.gaaf.proveedor.Proveedor;

@Service
public class UbicacionService {

	
	@Autowired
	private UbicacionRepository repository;
	
	
	@Autowired
	private MunicipioService municipioService;
	

	
	
	private  DatosDetalleUbicacion detalleUbicacion(Ubicacion ubicacion) {
		var detalleMunicipio = municipioService.obtenerPorId(ubicacion.getMunicipio().getId());
		return new DatosDetalleUbicacion(ubicacion.getId(), ubicacion.getDireccion(), detalleMunicipio);
	}
		
	
	
	public Ubicacion obtenerUbicacionPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Ubicacion no encontrada por el id: " + id));
	}
	
	public DatosDetalleUbicacion crear(DatosRegistrarUbicacion datos, Proveedor proveedor) {
		var municipio = municipioService.obtenerMunicipioPorId(datos.idMunicipio());
		var ubicacion = new Ubicacion(datos, municipio, proveedor);
		var nuevaUbicacion = repository.save(ubicacion);
		return detalleUbicacion(nuevaUbicacion);
		
	}
	
	public DatosDetalleUbicacion obtenerPorId(Long id) {
		var ubicacion = obtenerUbicacionPorId(id);
		return detalleUbicacion(ubicacion);
	}
	
	public List<DatosDetalleUbicacion> obtenerTodosPorNit(Long nit){
		var ubicaciones = repository.findAllByProveedor_Nit(nit);
		return ubicaciones.stream().map(u -> detalleUbicacion(u)).toList();
	}
	
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var ubicacion = obtenerUbicacionPorId(id);
		repository.delete(ubicacion);
		return new DatosDetalleResponse(200, "Ubicacion eliminada correctamente");
	}
	
}
