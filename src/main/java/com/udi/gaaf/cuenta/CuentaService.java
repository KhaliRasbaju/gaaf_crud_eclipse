package com.udi.gaaf.cuenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.entidad_bancaria.EntidadBancariaService;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.proveedor.Proveedor;

@Service
public class CuentaService {

	
	@Autowired
	private CuentaRepository repository;
	
	
	@Autowired
	private EntidadBancariaService entidadBancariaService;
	
	

	
	private DatosDetalleCuenta detalleCuenta(Cuenta cuenta, Long idEntidad) {
		
		var detalleEntidad = entidadBancariaService.obtenerPorId(idEntidad);
		
		return new DatosDetalleCuenta(cuenta.getId(),cuenta.getNumero(), cuenta.getTipo(), detalleEntidad);
		
	}
	
	
	public Cuenta obtenerCuentaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Cuenta no encontrada por el id: "+id.toString()));
	}
	
	
	public DatosDetalleCuenta crear(DatosRegistrarCuenta datos, Proveedor proveedor) {
		var entidad = entidadBancariaService.obtenerEntidadBancariaPorId(datos.idEntidad());
		var cuenta = new Cuenta(datos, entidad, proveedor);
		var nuevaCuenta = repository.save(cuenta);
		return detalleCuenta(nuevaCuenta, nuevaCuenta.getEntidad().getId());
	}
	
	@Transactional
	public DatosDetalleCuenta editar(DatosRegistrarCuenta datos, Long nit) {
		var entidad = datos.idEntidad() != null ? entidadBancariaService.obtenerEntidadBancariaPorId(datos.idEntidad()) : null;
		var cuenta = repository.findByProveedorNit(nit).orElseThrow(()-> new NotFoundException("Cuenta no existe por el nit: "+ nit));
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
		var cuentas = repository.findAllByProveedor_Nit(nit);
		return cuentas.stream().map(c -> detalleCuenta(c, c.getEntidad().getId())).toList();
	}
	
	
	
	public  DatosDetalleResponse eliminarPorId(Long id) {
		var cuenta = obtenerCuentaPorId(id);
		repository.delete(cuenta);
		return new DatosDetalleResponse(200, "Cuenta eliminada correctamente");
	}
	
	
	
	
}
