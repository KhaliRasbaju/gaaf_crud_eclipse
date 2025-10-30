package com.udi.gaaf.bodega;

import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleResponse;

/**
 * Servicio encargado de la lógica de negocio relacionada con las bodegas.
 * <p>
 * Esta clase se comunica con el repositorio {@link BodegaRepository} para realizar
 * operaciones CRUD sobre la entidad {@link Bodega}. 
 * Implementa validaciones y manejo de excepciones personalizadas 
 * para garantizar la integridad de los datos.
 * </p>
 *
 * <p><b>Responsabilidades principales:</b></p>
 * <ul>
 *   <li>Registrar nuevas bodegas.</li>
 *   <li>Actualizar información de bodegas existentes.</li>
 *   <li>Listar todas las bodegas registradas.</li>
 *   <li>Eliminar bodegas (verificando dependencias con inventarios).</li>
 * </ul>
 * 
 * @see Bodega
 * @see BodegaRepository
 * @see DatosRegistrarBodega
 * @see DatosDetalleBodega
 * @see DatosDetalleResponse
 */
@Service
public class BodegaService {

    /** Repositorio encargado de la persistencia de bodegas. */
    @Autowired
    private BodegaRepository repository;

    /**
     * Convierte una entidad {@link Bodega} en su representación de detalle.
     * 
     * @param bodega entidad de tipo {@link Bodega}
     * @return representación {@link DatosDetalleBodega}
     */
    private DatosDetalleBodega detalleBodega(Bodega bodega) {
        return new DatosDetalleBodega(bodega.getId(), bodega.getNombre(), bodega.getUbicacion());
    }

    /**
     * Obtiene una bodega por su identificador único.
     * 
     * @param id identificador de la bodega
     * @return entidad {@link Bodega}
     * @throws NotFoundException si no existe una bodega con el ID proporcionado
     */
    public Bodega obtenerBodegaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No hay bodega por el id: " + id));
    }

    /**
     * Crea una nueva bodega a partir de los datos recibidos.
     * 
     * @param datos información para registrar la bodega
     * @return representación detallada de la bodega creada
     */
    @Transactional
    public DatosDetalleBodega crear(DatosRegistrarBodega datos) {
        var bodega = new Bodega(datos);
        var bodegaNueva = repository.save(bodega);
        return detalleBodega(bodegaNueva);
    }

    /**
     * Edita una bodega existente con los nuevos datos proporcionados.
     * 
     * @param datos datos actualizados de la bodega
     * @param id identificador de la bodega a modificar
     * @return respuesta indicando el resultado de la operación
     */
    @Transactional
    public DatosDetalleResponse editar(DatosRegistrarBodega datos, Long id) {
        var bodega = obtenerBodegaPorId(id);

        if (datos.nombre() != null) bodega.setNombre(datos.nombre());
        if (datos.ubicacion() != null) bodega.setUbicacion(datos.ubicacion());

        repository.save(bodega);
        return new DatosDetalleResponse(200, "Bodega actualizada correctamente");
    }

    /**
     * Obtiene la información detallada de una bodega específica.
     * 
     * @param id identificador de la bodega
     * @return datos de detalle de la bodega
     */
    public DatosDetalleBodega obtenerPorId(Long id) {
        var bodega = obtenerBodegaPorId(id);
        return detalleBodega(bodega);
    }

    /**
     * Recupera todas las bodegas registradas en el sistema.
     * 
     * @return lista de {@link DatosDetalleBodega}
     */
    public List<DatosDetalleBodega> obtenerTodos() {
        var bodegas = repository.findAll();
        return bodegas.stream().map(this::detalleBodega).toList();
    }

    /**
     * Elimina una bodega del sistema si no posee inventarios asociados.
     * 
     * @param id identificador de la bodega a eliminar
     * @return respuesta con el resultado de la eliminación
     * @throws BadRequestException si la bodega tiene inventarios asociados
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var bodega = obtenerBodegaPorId(id);

        if (!bodega.getInventarios().isEmpty()) {
            throw new BadRequestException("No se puede eliminar la bodega ya que hay inventario");
        }

        repository.delete(bodega);
        return new DatosDetalleResponse(200, "Bodega eliminada correctamente");
    }
}
