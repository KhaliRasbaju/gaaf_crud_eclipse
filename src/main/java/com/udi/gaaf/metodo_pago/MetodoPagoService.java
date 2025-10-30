package com.udi.gaaf.metodo_pago;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udi.gaaf.common.DatosDetalleCommon;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.common.DatosRegistrarCommon;
import com.udi.gaaf.errors.BadRequestException;
import com.udi.gaaf.errors.NotFoundException;


/**
 * Servicio encargado de la lógica de negocio relacionada con los métodos de pago.
 * Permite crear, editar, listar, obtener y eliminar métodos de pago.
 */
@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repository;

    /**
     * Verifica si ya existe un método de pago con el mismo nombre.
     *
     * @param nombre Nombre del método de pago.
     * @throws BadRequestException Si ya existe un método de pago con ese nombre.
     */
    private void existeMetodoPago(String nombre) {
        var metodo = repository.findByNombre(nombre);
        if (metodo.isPresent()) {
            throw new BadRequestException("Ya existe un método por ese nombre");
        }
    }

    /**
     * Convierte una entidad {@link MetodoPago} a un DTO {@link DatosDetalleCommon}.
     *
     * @param pago Objeto de tipo MetodoPago.
     * @return Objeto DatosDetalleCommon con el id y nombre del método.
     */
    private DatosDetalleCommon detalleMetodoPago(MetodoPago pago) {
        return new DatosDetalleCommon(pago.getId(), pago.getNombre());
    }

    /**
     * Obtiene un método de pago por su ID.
     *
     * @param id ID del método de pago.
     * @return Objeto {@link MetodoPago} correspondiente al ID.
     * @throws NotFoundException Si no se encuentra el método.
     */
    public MetodoPago obtenerMetodoPagoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Método de pago no encontrado por el id: " + id));
    }

    /**
     * Crea un nuevo método de pago.
     *
     * @param datos Datos para registrar el método de pago.
     * @return Objeto {@link DatosDetalleCommon} con la información del nuevo método.
     * @throws BadRequestException Si el nombre ya está registrado.
     */
    public DatosDetalleCommon crear(DatosRegistrarCommon datos) {
        existeMetodoPago(datos.nombre());
        var metodo = new MetodoPago(datos);
        var nuevoMetodo = repository.save(metodo);
        return detalleMetodoPago(nuevoMetodo);
    }

    /**
     * Edita un método de pago existente.
     *
     * @param datos Datos actualizados (solo nombre).
     * @param id    ID del método de pago a editar.
     * @return Objeto {@link DatosDetalleResponse} con mensaje de éxito.
     * @throws NotFoundException  Si no se encuentra el método.
     */
    public DatosDetalleResponse editar(DatosRegistrarCommon datos, Long id) {
        var metodo = obtenerMetodoPagoPorId(id);
        if (!metodo.getNombre().equals(datos.nombre())) {
            metodo.setNombre(datos.nombre());
        }
        repository.save(metodo);
        return new DatosDetalleResponse(200, "Método de pago actualizado correctamente");
    }

    /**
     * Obtiene los detalles de un método de pago por ID.
     *
     * @param id ID del método.
     * @return Objeto {@link DatosDetalleCommon} con la información del método.
     */
    public DatosDetalleCommon obtenerPorId(Long id) {
        var metodo = obtenerMetodoPagoPorId(id);
        return detalleMetodoPago(metodo);
    }

    /**
     * Obtiene una lista de todos los métodos de pago registrados.
     *
     * @return Lista de {@link DatosDetalleCommon} con todos los métodos.
     */
    public List<DatosDetalleCommon> obtenerTodos() {
        var metodos = repository.findAll();
        return metodos.stream().map(this::detalleMetodoPago).toList();
    }

    /**
     * Elimina un método de pago por su ID, validando que no tenga medios asociados.
     *
     * @param id ID del método de pago.
     * @return Objeto {@link DatosDetalleResponse} con mensaje de éxito.
     * @throws BadRequestException Si el método tiene medios de pago asociados.
     * @throws NotFoundException   Si no se encuentra el método.
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var metodo = obtenerMetodoPagoPorId(id);

        if (!metodo.getMedio().isEmpty()) {
            throw new BadRequestException("No se puede eliminar: hay medios de pago asociados a este método");
        }

        repository.delete(metodo);
        return new DatosDetalleResponse(200, "Método de pago eliminado correctamente");
    }
}