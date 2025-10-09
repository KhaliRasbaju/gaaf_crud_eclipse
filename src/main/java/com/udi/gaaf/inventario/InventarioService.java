package com.udi.gaaf.inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.bodega.BodegaService;
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
	
	
	
	public DatosDetalleInventario crear(DatosRegistrarInventario datos) {
		var producto = productoService.obtenerProductoPorId(datos.idProducto());
		var bodega = bodegaService.obtenerBodegaPorId(datos.idBodega());
		var inventario = new Inventario(datos, bodega, producto);
		var nuevoInventario = repository.save(inventario);
		return detalleInventario(nuevoInventario);
	}
	
	public List<Inventario> obtenerInventarioPorBodegaId(Long id) {
		var inventarios =  repository.findByBodegaId(id);
		if(inventarios.isEmpty()) {
			throw new NotFoundException("No hay inventario por el id de la bodega: " + id);
		}
		return inventarios;
	}
	
	public List<DatosDetalleInventario> obtenerTodos(){
		var inventarios = repository.findAll();
		return inventarios.stream().map((i) -> detalleInventario(i) ).toList();
	}
	
}
