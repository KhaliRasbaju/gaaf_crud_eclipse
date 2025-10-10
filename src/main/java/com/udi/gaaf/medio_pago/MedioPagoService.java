package com.udi.gaaf.medio_pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.metodo_pago.MetodoPagoService;

@Service
public class MedioPagoService {

	@Autowired
	private MedioPagoRepository repository;
	
	@Autowired
	private MetodoPagoService metodoPagoService;
	
	private void existePorReferencia(String referencia) {
		var medio = repository.findByReferencia(referencia);
		
		if(medio.isPresent()) {
			throw new BadRequestException("Ya existe un medio de pago por esta referencia");
		}
	}
	
	private DatosDetalleMedioPago detalleMedioPago(MedioPago medio) {
		return new DatosDetalleMedioPago(medio.getId(), medio.getReferencia(), medio.getMetodo().getNombre());
	}
	
	public MedioPago obtenerMedioPagoPorId(Long id) {
		return repository.findById(id).orElseThrow(()-> new NotFoundException("Medio de pago no encontrado por el id: "+id));
	}
		
	public DatosDetalleMedioPago crear(DatosRegistrarMedioPago datos) {
		existePorReferencia(datos.referencia());
		var metodo = metodoPagoService.obtenerMetodoPagoPorId(datos.idMetodoPago());
		var medio = new MedioPago(datos, metodo);
		var nuevoMedio = repository.save(medio);
		return detalleMedioPago(nuevoMedio);
	}
	
	public DatosDetalleMedioPago obtenerPorId(Long id) {
		var medio = obtenerMedioPagoPorId(id);
		return detalleMedioPago(medio);
	}
	
	public DatosDetalleResponse eliminarPorid(Long id) {
		var medio = obtenerMedioPagoPorId(id);
		repository.delete(medio);
		return new DatosDetalleResponse(200, "Medio de pago borrado correctamenta");
	}

}
