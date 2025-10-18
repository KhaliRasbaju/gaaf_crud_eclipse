package com.udi.gaaf.proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.cuenta.CuentaService;
import com.udi.gaaf.cuenta.DatosDetalleCuenta;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.ubicacion.DatosDetalleUbicacion;
import com.udi.gaaf.ubicacion.UbicacionService;


@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	
	@Autowired
	private UbicacionService ubicacionService;
	
	@Autowired 
	private CuentaService cuentaService;
	

	private void existsProveedorByNit(Long nit) {
		var proveedor = repository.findById(nit);
		
		if(proveedor.isPresent()) {
			throw new BadRequestException("Ya existe un proveedor por el nit: "+ nit);
		}
		
		
	}
	
	private DatosDetalleProveedor detalleProveedor(Proveedor proveedor, Boolean cargar) {
		
		    List<DatosDetalleCuenta> cuentas = List.of();
		    List<DatosDetalleUbicacion> ubicaciones = List.of();

		    if(cargar) {
		        cuentas = cuentaService.obtenerTodosPorNit(proveedor.getNit());
		        ubicaciones = ubicacionService.obtenerTodosPorNit(proveedor.getNit());
		        System.out.println(cuentas);
		        System.out.println(ubicaciones);
		    }
		    
		    return new DatosDetalleProveedor(
		    		proveedor.getNit(),
		    		proveedor.getNombre(),
		    		proveedor.getTelefono(),
		    		proveedor.getCorreo(),
		    		ubicaciones,
		    		cuentas
		    		);
		    
		    
		
	}
	
	
	public Proveedor obtenerProveedorPorNit(Long nit) {
		return repository.findById(nit).orElseThrow(()-> new NotFoundException("No se encontro proveedor por el nit: "+nit));
	}
	
	
	@Transactional
	public DatosDetalleProveedor crear(DatosRegistrarProveedor datos) {
		existsProveedorByNit(datos.nit());
		var proveedor = new Proveedor(datos);
		var nuevoProveedor = repository.save(proveedor);
		cuentaService.crear(datos.cuenta(), nuevoProveedor);
		ubicacionService.crear(datos.ubicacion(), nuevoProveedor);
		var proveedorFinal = obtenerProveedorPorNit(nuevoProveedor.getNit());
		return detalleProveedor(proveedorFinal, true);
	}
	
	public DatosDetalleResponse editar(DatosRegistrarProveedor datos, Long nit) {
		var proveedor = obtenerProveedorPorNit(nit);
		if(proveedor.getNombre() != datos.nombre()) proveedor.setNombre(datos.nombre());
		if(proveedor.getCorreo() != datos.correo()) proveedor.setCorreo(datos.correo());
		if(proveedor.getTelefono() != datos.telefono()) proveedor.setTelefono(datos.telefono());
		if(proveedor.getNit() != datos.nit()) proveedor.setNit(nit);
		var proveedorActualizado = repository.save(proveedor);
		cuentaService.editar(datos.cuenta(), nit);
		ubicacionService.editar(datos.ubicacion(), proveedorActualizado);
		return new DatosDetalleResponse(200, "Proveedor actualizado");
	}
	
	public DatosDetalleResponse cambiarEstado(Long nit) {
		var proveedor = obtenerProveedorPorNit(nit);
		if(proveedor.getActivo()) {
			proveedor.setActivo(false);
		} else {
			proveedor.setActivo(true);
		}
		
		return new DatosDetalleResponse(200, "Estado del proveedor cambiado correctamente");
	}
	
	
	public DatosDetalleProveedor obtenerPorNit(Long nit) {
		var proveedor = obtenerProveedorPorNit(nit);
		return detalleProveedor(proveedor, true);	
		
	}
	
	public List<DatosDetalleProveedor> obtenerTodos() {
		var proveedores = repository.findAll();
		return proveedores.stream()
				.map(p -> detalleProveedor(p, false))
				.toList();
	}
	
	public DatosDetalleResponse eliminarPorNit(Long nit) {
		var proveedor = obtenerProveedorPorNit(nit);
		
		if(!proveedor.getPedidos().isEmpty()) {
			throw new BadRequestException("No se puede eliminar proveedor ya tiene pedidos asociados. Desactiva al proveedor.");
		}
		System.out.println(proveedor.getUbicaciones().getId());
		repository.delete(proveedor);
		return new DatosDetalleResponse(200, "Proveedor eliminado correctamente");
		
	}
	
}
