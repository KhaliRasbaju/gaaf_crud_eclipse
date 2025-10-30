package com.udi.gaaf.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;

/**
 * Servicio encargado de gestionar la lógica de negocio de los productos.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    /**
     * Convierte una entidad {@link Producto} en un DTO {@link DatosDetalleProducto}.
     *
     * @param producto Entidad producto.
     * @return DTO con los detalles del producto.
     */
    private DatosDetalleProducto detalleProducto(Producto producto) {
        return new DatosDetalleProducto(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getTipo()
        );
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id ID del producto.
     * @return Entidad del producto.
     * @throws NotFoundException Si no existe un producto con ese ID.
     */
    public Producto obtenerProductoPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado por el id: " + id));
    }

    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param datos Datos del producto a registrar.
     * @return DTO con los detalles del producto creado.
     */
    @Transactional
    public DatosDetalleProducto crear(DatosRegistrarProducto datos) {
        var producto = new Producto(datos);
        var nuevoProducto = repository.save(producto);
        return detalleProducto(nuevoProducto);
    }

    /**
     * Edita un producto existente.
     *
     * @param datos Datos actualizados del producto.
     * @param id    ID del producto a editar.
     * @return Respuesta con el estado de la operación.
     * @throws NotFoundException Si el producto no existe.
     */
    @Transactional
    public DatosDetalleResponse editar(DatosRegistrarProducto datos, Long id) {
        var producto = obtenerProductoPorId(id);

        if (!datos.nombre().equals(producto.getNombre())) producto.setNombre(datos.nombre());
        if (!datos.descripcion().equals(producto.getDescripcion())) producto.setDescripcion(datos.descripcion());
        if (datos.tipo() != producto.getTipo()) producto.setTipo(datos.tipo());

        repository.save(producto);
        return new DatosDetalleResponse(200, "Producto actualizado correctamente");
    }

    /**
     * Obtiene los detalles de un producto por su ID.
     *
     * @param id ID del producto.
     * @return DTO con los detalles del producto.
     */
    public DatosDetalleProducto obtenerPorId(Long id) {
        var producto = obtenerProductoPorId(id);
        return detalleProducto(producto);
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * @return Lista de DTOs con todos los productos.
     */
    public List<DatosDetalleProducto> obtenerTodos() {
        var productos = repository.findAll();
        return productos.stream()
            .map(this::detalleProducto)
            .toList();
    }

    /**
     * Obtiene varios productos por una lista de IDs.
     *
     * @param productosIds Lista de IDs de los productos.
     * @return Lista de DTOs con los productos encontrados.
     */
    public List<DatosDetalleProducto> obtenerTodosPorId(List<Long> productosIds) {
        var productos = repository.findAllById(productosIds);
        return productos.stream()
            .map(this::detalleProducto)
            .toList();
    }

    /**
     * Elimina un producto por su ID, validando que no tenga asociaciones activas.
     *
     * @param id ID del producto a eliminar.
     * @return Respuesta con el estado de la operación.
     * @throws BadRequestException Si el producto tiene pedidos o inventarios asociados.
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var producto = obtenerProductoPorId(id);

        if (!producto.getDetallePedidos().isEmpty() || !producto.getInventarios().isEmpty()) {
            throw new BadRequestException("No se puede eliminar: el producto tiene asociaciones activas");
        }

        repository.delete(producto);
        return new DatosDetalleResponse(200, "Producto eliminado correctamente");
    }
}
