package com.udi.gaaf.bodega;

import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;

@Service
public class BodegaService {
	@Autowired
	private BodegaRepository repository;
	
	
	private DatosDetalleBodega detalleBodega(Bodega bodega) {
		return new DatosDetalleBodega(bodega.getId(), bodega.getNombre(), bodega.getUbicacion());
	}
	
	public Bodega obtenerBodegaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new  NotFoundException("No hay bodega por el id: " + id));
	}
	
	@Transactional
	public DatosDetalleBodega crear(DatosRegistrarBodega datos) {
		var bodega = new Bodega(datos); 
		var bodegaNueva = repository.save(bodega);
		return  detalleBodega(bodegaNueva);	
	}
	
	@Transactional
	public DatosDetalleResponse editar(DatosRegistrarBodega datos, Long id) {
		var bodega = obtenerBodegaPorId(id);
		if(datos.nombre() != null) bodega.setNombre(datos.nombre());
		if(datos.ubicacion() != null) bodega.setUbicacion(datos.ubicacion());
		repository.save(bodega);
		return new DatosDetalleResponse(200, "Bodega actualizada correctamente");
	}
	
	
	
	public DatosDetalleBodega obtenerPorId(Long id) {
		var bodega = obtenerBodegaPorId(id);
		return  detalleBodega(bodega);
	}
	

	public List<DatosDetalleBodega> obtenerTodos(){
		var bodegas = repository.findAll();
		return bodegas.stream().map(bd -> detalleBodega(bd)).toList();
	}
	
	
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var bodega  = obtenerBodegaPorId(id);
		
		if(!bodega.getInventarios().isEmpty()) {
			throw new BadRequestException("No se puede eliminar la bodega ya que hay inventario");
		}
		
		
		repository.delete(bodega);
		return new DatosDetalleResponse(200, "Bodega eliminada correctamente");
	}
	
}
