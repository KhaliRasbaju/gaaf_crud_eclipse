package com.udi.gaaf.metodo_pago;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;

@Service
public class MetodoPagoService {

	
	@Autowired
	private MetodoPagoRepository repository;
	
	private void existeMetodoPago(String nombre) {
		var metodo = repository.findByNombre(nombre);
		
		if(metodo.isPresent()) {
			throw new BadRequestException("Ya existe un metodo por ese nombre");
		}
	}
	
	private DatosDetalleCommon detalleMetodoPago(MetodoPago pago) {
		return new DatosDetalleCommon(pago.getId(), pago.getNombre());
	}
	
	public MetodoPago obtenerMetodoPagoPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Metodo de pago no encontrado por el id: "+ id));
	}
	
	public DatosDetalleCommon crear(DatosRegistrarCommon datos) {
		existeMetodoPago(datos.nombre());
		var metodo = new MetodoPago(datos);
		var nuevoMetodo = repository.save(metodo);
		return detalleMetodoPago(nuevoMetodo);
	}
	
	
	public DatosDetalleCommon editar(DatosRegistrarCommon datos, Long id) {
		var metodo = obtenerMetodoPagoPorId(id);
		if(datos.nombre() != metodo.getNombre()) metodo.setNombre(datos.nombre());
		var metodoActualizado = repository.save(metodo);
		return detalleMetodoPago(metodoActualizado);
	}
	
	public DatosDetalleCommon obtenerPorId(Long id) {
		var metodo = obtenerMetodoPagoPorId(id);
		return detalleMetodoPago(metodo);
	}
	
	public List<DatosDetalleCommon> obtenerTodos(){
		var metodos = repository.findAll();
		return metodos.stream().map(m -> detalleMetodoPago(m)).toList();
	}
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var metodo = obtenerMetodoPagoPorId(id);
		repository.delete(metodo);
		return new DatosDetalleResponse(200, "Metodo de pago eliminado correctamente");
	}
	
	
}
