package com.udi.gaaf.entidad_bancaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;

/**
 * Servicio encargado de gestionar las operaciones relacionadas
 * con las entidades bancarias, incluyendo creación, edición,
 * eliminación y consulta.
 */
@Service
public class EntidadBancariaService {

    /** Repositorio que maneja las operaciones de persistencia. */
    @Autowired
    public EntidadBancariaRepository repository;

    /**
     * Convierte una entidad bancaria en un objeto de detalle común.
     *
     * @param entidad entidad bancaria a transformar.
     * @return un objeto {@link DatosDetalleCommon} con la información resumida.
     */
    private DatosDetalleCommon detalleEntidad(EntidadBancaria entidad) {
        return new DatosDetalleCommon(entidad.getId(), entidad.getNombre());
    }

    /**
     * Obtiene una entidad bancaria por su identificador.
     *
     * @param id identificador único de la entidad bancaria.
     * @return la entidad bancaria correspondiente.
     * @throws NotFoundException si no se encuentra la entidad.
     */
    public EntidadBancaria obtenerEntidadBancariaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entidad bancaria no encontrada"));
    }

    /**
     * Crea una nueva entidad bancaria en el sistema.
     *
     * @param datos objeto con la información para registrar la entidad.
     * @return un objeto {@link DatosDetalleCommon} con los datos del registro creado.
     */
    @Transactional
    public DatosDetalleCommon crear(DatosRegistrarCommon datos) {
        var entidad = new EntidadBancaria(datos);
        var nuevaEntidad = repository.save(entidad);
        return detalleEntidad(nuevaEntidad);
    }

    /**
     * Edita los datos de una entidad bancaria existente.
     *
     * @param id identificador de la entidad a editar.
     * @param datos objeto con los nuevos valores a actualizar.
     * @return un objeto {@link DatosDetalleResponse} con el estado de la operación.
     * @throws NotFoundException si la entidad no existe.
     */
    @Transactional
    public DatosDetalleResponse editar(Long id, DatosRegistrarCommon datos) {
        var entidad = obtenerEntidadBancariaPorId(id);
        if (datos.nombre() != null) entidad.setNombre(datos.nombre());
        repository.save(entidad);
        return new DatosDetalleResponse(200, "Entidad bancaria actualizada correctamente");
    }

    /**
     * Obtiene la información de una entidad bancaria específica.
     *
     * @param id identificador de la entidad.
     * @return un objeto {@link DatosDetalleCommon} con la información solicitada.
     */
    public DatosDetalleCommon obtenerPorId(Long id) {
        var entidad = obtenerEntidadBancariaPorId(id);
        return detalleEntidad(entidad);
    }

    /**
     * Obtiene todas las entidades bancarias registradas.
     *
     * @return una lista de objetos {@link DatosDetalleCommon} con la información de cada entidad.
     */
    public List<DatosDetalleCommon> obtenerTodos() {
        var entidades = repository.findAll();
        return entidades.stream().map(en -> detalleEntidad(en)).toList();
    }

    /**
     * Elimina una entidad bancaria por su identificador.
     * Solo es posible eliminarla si no tiene cuentas asociadas.
     *
     * @param id identificador de la entidad bancaria.
     * @return un objeto {@link DatosDetalleResponse} indicando el resultado de la operación.
     * @throws BadRequestException si existen cuentas asociadas a la entidad.
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var entidad = obtenerEntidadBancariaPorId(id);
        if (!entidad.getCuentas().isEmpty()) {
            throw new BadRequestException("No se puede eliminar la entidad bancaria. Existen cuentas asociadas a esta entidad.");
        }
        repository.delete(entidad);
        return new DatosDetalleResponse(200, "Entidad Bancaria eliminada correctamente");
    }
}
