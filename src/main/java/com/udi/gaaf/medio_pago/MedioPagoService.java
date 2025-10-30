package com.udi.gaaf.medio_pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.metodo_pago.MetodoPagoService;

/**
 * Servicio encargado de la gestión de medios de pago.
 * Incluye validaciones y operaciones CRUD.
 */
@Service
public class MedioPagoService {

    @Autowired
    private MedioPagoRepository repository;

    @Autowired
    private MetodoPagoService metodoPagoService;

    /**
     * Verifica si ya existe un medio de pago con la misma referencia.
     *
     * @param referencia Referencia a validar.
     * @throws BadRequestException Si ya existe un medio de pago con esa referencia.
     */
    private void existePorReferencia(String referencia) {
        var medio = repository.findByReferencia(referencia);
        if (medio.isPresent()) {
            throw new BadRequestException("Ya existe un medio de pago por esta referencia");
        }
    }

    /**
     * Convierte un objeto MedioPago a un DTO detallado.
     *
     * @param medio Objeto MedioPago.
     * @return Objeto {@link DatosDetalleMedioPago} con los detalles del medio.
     */
    private DatosDetalleMedioPago detalleMedioPago(MedioPago medio) {
        return new DatosDetalleMedioPago(medio.getId(), medio.getReferencia(), medio.getMetodo().getNombre());
    }

    /**
     * Obtiene un medio de pago por su ID.
     *
     * @param id ID del medio de pago.
     * @return Objeto {@link MedioPago} encontrado.
     * @throws NotFoundException Si no se encuentra el medio.
     */
    public MedioPago obtenerMedioPagoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Medio de pago no encontrado por el id: " + id));
    }

    /**
     * Crea un nuevo medio de pago.
     *
     * @param datos Datos de registro del medio de pago.
     * @return Objeto {@link DatosDetalleMedioPago} con la información del medio creado.
     * @throws BadRequestException Si la referencia ya está registrada.
     */
    public DatosDetalleMedioPago crear(DatosRegistrarMedioPago datos) {
        existePorReferencia(datos.referencia());
        var metodo = metodoPagoService.obtenerMetodoPagoPorId(datos.idMetodoPago());
        var medio = new MedioPago(datos, metodo);
        var nuevoMedio = repository.save(medio);
        return detalleMedioPago(nuevoMedio);
    }

    /**
     * Edita un medio de pago asociado a un pedido.
     *
     * @param datos Datos actualizados del medio de pago.
     * @param id    ID del pedido asociado.
     * @return Objeto {@link MedioPago} actualizado.
     * @throws NotFoundException Si no se encuentra el medio de pago.
     */
    public MedioPago editar(DatosRegistrarMedioPago datos, Long id) {
        var metodo = metodoPagoService.obtenerMetodoPagoPorId(datos.idMetodoPago());
        var medio = repository.findByPedidoId(id)
                .orElseThrow(() -> new NotFoundException("Medio de pago no encontrado por el producto id: " + id));

        if (!medio.getReferencia().equals(datos.referencia())) medio.setReferencia(datos.referencia());
        if (!medio.getMetodo().getId().equals(datos.idMetodoPago())) medio.setMetodo(metodo);

        return repository.save(medio);
    }

    /**
     * Obtiene un medio de pago por su ID en formato DTO.
     *
     * @param id ID del medio de pago.
     * @return Objeto {@link DatosDetalleMedioPago}.
     */
    public DatosDetalleMedioPago obtenerPorId(Long id) {
        var medio = obtenerMedioPagoPorId(id);
        return detalleMedioPago(medio);
    }

    /**
     * Elimina un medio de pago si no está asociado a un pedido.
     *
     * @param id ID del medio de pago.
     * @return Respuesta con el código y mensaje de eliminación.
     * @throws BadRequestException Si existe un pedido asociado.
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var medio = obtenerMedioPagoPorId(id);
        if (medio.getPedido() != null) {
            throw new BadRequestException("No se puede eliminar ya hay un pedido asociado a este medio de pago");
        }
        repository.delete(medio);
        return new DatosDetalleResponse(200, "Medio de pago borrado correctamente");
    }
}