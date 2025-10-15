package com.udi.gaaf.inventario;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.bodega.BodegaService;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.producto.ProductoService;

@Service
public class InventarioService {

	@Autowired
	private InventarioRepository repository;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private BodegaService bodegaService;

	private DatosDetalleInventario detalleInventario(Inventario inventario) {
		return new DatosDetalleInventario(inventario.getCantidad(), inventario.getFecha(), inventario.getBodega().getNombre(), inventario.getProducto().getNombre());
	}
	
	
	public Inventario obtenerPorInventarioIds(DatosBuscarInventarioIds datos) {
		return repository.findById_IdBodegaAndId_IdProducto(datos.idBodega(), datos.idProducto()).orElseThrow(()->  new NotFoundException("No hay inventario por el id de la bodega: " + datos.toString()) );
	}
	
	
	
	public DatosDetalleInventario crear(DatosRegistrarInventario datos, Boolean eliminar) {
		
		var producto = productoService.obtenerProductoPorId(datos.idProducto());
		var bodega = bodegaService.obtenerBodegaPorId(datos.idBodega());
		if(eliminar) {
			var buscar = new DatosBuscarInventarioIds(bodega.getId(), producto.getId());
			var inventario = obtenerPorInventarioIds(buscar);
			if(inventario.getCantidad() < datos.cantidad()) {
				throw new BadRequestException("No se puede sacar esa cantidad ya que solo hay: " + inventario.getCantidad() + " revisa el stock");
			}
			var cantidad = inventario.getCantidad() - datos.cantidad();
			inventario.setFecha(datos.fecha());
			inventario.setCantidad(cantidad);
			repository.save(inventario);
			return detalleInventario(inventario);
		}
		
		var inventario = new Inventario(datos, bodega, producto);
		var nuevoInventario = repository.save(inventario);
		return detalleInventario(nuevoInventario);
	}
	
	
	
}
