package com.udi.gaaf.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.bodega.BodegaService;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.producto.ProductoService;

/**
 * Servicio que gestiona las operaciones relacionadas con el inventario,
 * incluyendo creación, actualización, búsqueda y validaciones.
 */
@Service
public class InventarioService {

    /** Repositorio encargado de la persistencia de inventarios. */
    @Autowired
    private InventarioRepository repository;

    /** Servicio de productos para obtener información de los productos asociados. */
    @Autowired
    private ProductoService productoService;

    /** Servicio de bodegas para obtener información de las bodegas asociadas. */
    @Autowired
    private BodegaService bodegaService;

    /**
     * Convierte un objeto {@link Inventario} a un {@link DatosDetalleInventario}.
     *
     * @param inventario inventario a transformar.
     * @return objeto con los detalles del inventario.
     */
    private DatosDetalleInventario detalleInventario(Inventario inventario) {
        return new DatosDetalleInventario(
            inventario.getCantidad(),
            inventario.getFecha(),
            inventario.getBodega().getNombre(),
            inventario.getProducto().getNombre()
        );
    }

    /**
     * Obtiene un inventario a partir de los identificadores de bodega y producto.
     *
     * @param datos objeto que contiene los identificadores del inventario.
     * @return el inventario encontrado.
     * @throws NotFoundException si no existe el inventario solicitado.
     */
    public Inventario obtenerPorInventarioIds(DatosBuscarInventarioIds datos) {
        return repository.findById_IdBodegaAndId_IdProducto(datos.idBodega(), datos.idProducto())
                .orElseThrow(() -> new NotFoundException("No hay inventario por el id de la bodega: " + datos.toString()));
    }

    /**
     * Crea o actualiza un inventario dependiendo del valor de la bandera "eliminar".
     * Si eliminar es {@code true}, se descuenta stock; si es {@code false}, se crea uno nuevo.
     *
     * @param datos objeto con la información del inventario.
     * @param eliminar indica si se debe eliminar (descontar stock) o crear un nuevo registro.
     * @return un objeto {@link DatosDetalleInventario} con la información del inventario actualizado o creado.
     * @throws BadRequestException si se intenta eliminar más cantidad de la disponible.
     */
    public DatosDetalleInventario crear(DatosRegistrarInventario datos, Boolean eliminar) {
        var producto = productoService.obtenerProductoPorId(datos.idProducto());
        var bodega = bodegaService.obtenerBodegaPorId(datos.idBodega());

        if (eliminar) {
            var buscar = new DatosBuscarInventarioIds(bodega.getId(), producto.getId());
            var inventario = obtenerPorInventarioIds(buscar);

            if (inventario.getCantidad() < datos.cantidad()) {
                throw new BadRequestException("No se puede sacar esa cantidad ya que solo hay: " 
                        + inventario.getCantidad() + " revisa el stock");
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
