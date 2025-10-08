package com.udi.gaaf.proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.cuenta.Cuenta;
import com.udi.gaaf.cuenta.CuentaService;
import com.udi.gaaf.entidad_bancaria.EntidadBancaria;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.ubicacion.Ubicacion;
import com.udi.gaaf.ubicacion.UbicacionService;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	
	@Autowired
	private UbicacionService ubicacionService;
	
	@Autowired 
	private CuentaService cuentaService;
	

	
	private DatosDetalleProveedor detalleProveedor(Proveedor proveedor) {
		var detalleCuenta = cuentaService.obtenerTodosPorNit(proveedor.getNit());
		var detalleUbicacion = ubicacionService.obtenerTodosPorNit(proveedor.getNit());
		return new DatosDetalleProveedor(proveedor.getNit(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getCorreo(), detalleUbicacion, detalleCuenta);
	}
	
	
	public Proveedor obtenerProveedorPorNit(Long nit) {
		return repository.findById(nit).orElseThrow(()-> new NotFoundException("No se encontro proveedor por el nit: "+nit));
	}
	
	
	public DatosDetalleProveedor crear(DatosRegistrarProveedor datos) {
		var proveedor = new Proveedor(datos);
		var crearCuenta = cuentaService.crear(datos.cuenta());
		var cuenta = cuentaService.obtenerCuentaPorId(crearCuenta.id());
		var crearUbicacion = ubicacionService.crear(datos.ubicacion());
		var ubicacion = ubicacionService.obtenerUbicacionPorId(crearUbicacion.id());
		proveedor.setCuentas(List.of(cuenta));
		proveedor.setUbicaciones(List.of(ubicacion));
		var nuevoProveedor = repository.save(proveedor);
		return detalleProveedor(nuevoProveedor);
	}
	
	public List<DatosDetalleProveedor> findAll() {
		var proveedores = repository.findAll();
		return proveedores.stream()
				.map(p -> detalleProveedor(p))
				.toList();
	}
	
}
