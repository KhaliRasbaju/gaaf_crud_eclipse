package com.udi.gaaf.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository repository;
	
	
	
	private DatosDetalleProducto detalleProducto(Producto producto) {
		return new DatosDetalleProducto(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getTipo());
	}
	
	public Producto obtenerProductoPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado por el id: " + id));
	}
	
	
	@Transactional
	public DatosDetalleProducto crear(DatosRegistrarProducto datos) {
		var producto = new Producto(datos);
		var nuevoProducto = repository.save(producto);
		return detalleProducto(nuevoProducto);
	}
	
	@Transactional
	public DatosDetalleResponse editar(DatosRegistrarProducto datos, Long id) {
		var producto = obtenerProductoPorId(id);
		if(datos.nombre()!= producto.getNombre()) producto.setNombre(datos.nombre());
		if(datos.descripcion()!= producto.getDescripcion()) producto.setDescripcion(datos.descripcion());
		if(datos.tipo() != producto.getTipo()) producto.setTipo(datos.tipo());
		var productoActualizado = repository.save(producto);
		return new DatosDetalleResponse(200, "Producto actualizado correctamente");
	}
	

	public DatosDetalleProducto obtenerPorId(Long id) {
		var producto = obtenerProductoPorId(id);
		return detalleProducto(producto);
		
	}
	
	public List<DatosDetalleProducto> obtenerTodos(){
		var productos = repository.findAll();
		return productos.stream()
				.map(pr -> detalleProducto(pr))
				.toList();
	}
	
	public List<DatosDetalleProducto> obtenerTodosPorId(List<Long> productosIds){
		var productos = repository.findAllById(productosIds);
		return productos.stream()
				.map(pr -> detalleProducto(pr))
				.toList();
	}
	
	public DatosDetalleResponse eliminarPorId(Long id) {
		var producto = obtenerProductoPorId(id);
		if(producto.getDetallePedidos() != null || producto.getInventarios() != null) {
			throw new BadRequestException("No se puede eliminar ya hay productos asociados");
		}
		repository.delete(producto);
		return new DatosDetalleResponse(200, "Producto eliminado correctamente");
	}
}
