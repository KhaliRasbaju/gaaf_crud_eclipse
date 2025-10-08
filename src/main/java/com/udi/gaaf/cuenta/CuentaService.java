package com.udi.gaaf.cuenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.entidad_bancaria.EntidadBancariaService;
import com.udi.gaaf.errors.NotFoundException;

@Service
public class CuentaService {

	
	@Autowired
	private CuentaRepository repository;
	
	
	@Autowired
	private EntidadBancariaService entidadBancariaService;
	
	
	
	public Cuenta obtenerCuentaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Cuenta no encontrada por el id: "+id.toString()));
	}
	
	private DatosDetalleCuenta detalleCuenta(Cuenta cuenta, Long id_entidad) {
		
		var detalleEntidad = entidadBancariaService.obtenerPorId(id_entidad);
		
		return new DatosDetalleCuenta(cuenta.getId(),cuenta.getNumero(), cuenta.getTipo(), detalleEntidad);
		
	}
	
	
	
	public DatosDetalleCuenta crear(DatosRegistrarCuenta datos) {
		var entidad = entidadBancariaService.obtenerEntidadBancariaPorId(datos.id_entidad());
		var cuenta = new Cuenta(datos, entidad);
		var nuevaCuenta = repository.save(cuenta);
		return detalleCuenta(nuevaCuenta, nuevaCuenta.getEntidad().getId());
	}
	
	
	public DatosDetalleCuenta editar(DatosRegistrarCuenta datos, Long id) {
		var entidad = datos.id_entidad() != null ? entidadBancariaService.obtenerEntidadBancariaPorId(datos.id_entidad()) : null;
		var cuenta = obtenerCuentaPorId(id);
		if(entidad!= null) cuenta.setEntidad(entidad);
		if(cuenta.getNumero() != datos.numero()) cuenta.setNumero(datos.numero());
		if(cuenta.getTipo() != datos.tipo()) cuenta.setTipo(datos.tipo());
		var cuentaActualizada = repository.save(cuenta);
		return detalleCuenta(cuentaActualizada, cuentaActualizada.getEntidad().getId());
	}
	
	
	public DatosDetalleCuenta obtenerPorId(Long id) {
		var cuenta = obtenerCuentaPorId(id);
		return detalleCuenta(cuenta, cuenta.getEntidad().getId());
	}
	
	
	public List<DatosDetalleCuenta> obtenerTodos(){
		var cuentas = repository.findAll();
		return cuentas.stream().map(c -> detalleCuenta(c, c.getEntidad().getId())).toList();
	}
	
	public List<DatosDetalleCuenta> obtenerTodosPorNit(Long nit){
		var cuentas = repository.findAllByProveedorByNit(nit);
		return cuentas.stream().map(c -> detalleCuenta(c, c.getEntidad().getId())).toList();
	}
	
	
	
	public  DatosDetalleResponse eliminarPorId(Long id) {
		var cuenta = obtenerCuentaPorId(id);
		repository.delete(cuenta);
		return new DatosDetalleResponse(200, "Cuenta eliminada correctamente");
	}
	
	
	
	
}
