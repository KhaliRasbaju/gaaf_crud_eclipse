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

/**
 * Servicio encargado de la gestión de proveedores,
 * incluyendo su creación, edición, eliminación y cambio de estado.
 */
@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Autowired
    private UbicacionService ubicacionService;

    @Autowired
    private CuentaService cuentaService;

    /**
     * Verifica si ya existe un proveedor con el NIT especificado.
     *
     * @param nit NIT del proveedor.
     * @throws BadRequestException Si el proveedor ya existe.
     */
    private void existsProveedorByNit(Long nit) {
        var proveedor = repository.findById(nit);
        if (proveedor.isPresent()) {
            throw new BadRequestException("Ya existe un proveedor con el NIT: " + nit);
        }
    }

    /**
     * Convierte una entidad {@link Proveedor} en un DTO {@link DatosDetalleProveedor}.
     *
     * @param proveedor Entidad proveedor.
     * @param cargar    Si es {@code true}, se cargan las cuentas y ubicaciones asociadas.
     * @return DTO con los detalles del proveedor.
     */
    private DatosDetalleProveedor detalleProveedor(Proveedor proveedor, Boolean cargar) {
        List<DatosDetalleCuenta> cuentas = List.of();
        List<DatosDetalleUbicacion> ubicaciones = List.of();

        if (cargar) {
            cuentas = cuentaService.obtenerTodosPorNit(proveedor.getNit());
            ubicaciones = ubicacionService.obtenerTodosPorNit(proveedor.getNit());
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

    /**
     * Obtiene un proveedor por su NIT.
     *
     * @param nit NIT del proveedor.
     * @return Entidad del proveedor.
     * @throws NotFoundException Si no se encuentra un proveedor con el NIT indicado.
     */
    public Proveedor obtenerProveedorPorNit(Long nit) {
        return repository.findById(nit)
            .orElseThrow(() -> new NotFoundException("No se encontró proveedor con el NIT: " + nit));
    }

    /**
     * Crea un nuevo proveedor junto con su ubicación y cuenta asociada.
     *
     * @param datos Datos del proveedor a registrar.
     * @return DTO con los detalles del proveedor creado.
     * @throws BadRequestException Si ya existe un proveedor con el mismo NIT.
     */
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

    /**
     * Edita un proveedor existente.
     *
     * @param datos Datos actualizados del proveedor.
     * @param nit   NIT del proveedor a editar.
     * @return Respuesta con el estado de la operación.
     * @throws NotFoundException Si no existe el proveedor.
     */
    @Transactional
    public DatosDetalleResponse editar(DatosRegistrarProveedor datos, Long nit) {
        var proveedor = obtenerProveedorPorNit(nit);

        if (!datos.nombre().equals(proveedor.getNombre())) proveedor.setNombre(datos.nombre());
        if (!datos.correo().equals(proveedor.getCorreo())) proveedor.setCorreo(datos.correo());
        if (!datos.telefono().equals(proveedor.getTelefono())) proveedor.setTelefono(datos.telefono());

        repository.save(proveedor);
        cuentaService.editar(datos.cuenta(), nit);
        ubicacionService.editar(datos.ubicacion(), proveedor);

        return new DatosDetalleResponse(200, "Proveedor actualizado correctamente");
    }

    /**
     * Cambia el estado (activo/inactivo) de un proveedor.
     *
     * @param nit NIT del proveedor.
     * @return Respuesta indicando el cambio de estado.
     * @throws NotFoundException Si el proveedor no existe.
     */
    public DatosDetalleResponse cambiarEstado(Long nit) {
        var proveedor = obtenerProveedorPorNit(nit);
        proveedor.setActivo(!proveedor.getActivo());
        return new DatosDetalleResponse(200, "Estado del proveedor cambiado correctamente");
    }

    /**
     * Obtiene los detalles de un proveedor por su NIT, incluyendo sus relaciones.
     *
     * @param nit NIT del proveedor.
     * @return DTO con los detalles completos del proveedor.
     */
    public DatosDetalleProveedor obtenerPorNit(Long nit) {
        var proveedor = obtenerProveedorPorNit(nit);
        return detalleProveedor(proveedor, true);
    }

    /**
     * Obtiene todos los proveedores registrados.
     *
     * @return Lista de DTOs con los proveedores (sin relaciones cargadas).
     */
    public List<DatosDetalleProveedor> obtenerTodos() {
        var proveedores = repository.findAll();
        return proveedores.stream()
            .map(p -> detalleProveedor(p, false))
            .toList();
    }

    /**
     * Elimina un proveedor de forma permanente si no tiene pedidos asociados.
     *
     * @param nit NIT del proveedor a eliminar.
     * @return Respuesta con el resultado de la operación.
     * @throws BadRequestException Si el proveedor tiene pedidos asociados.
     */
    public DatosDetalleResponse eliminarPorNit(Long nit) {
        var proveedor = obtenerProveedorPorNit(nit);

        if (!proveedor.getPedidos().isEmpty()) {
            throw new BadRequestException("No se puede eliminar: el proveedor tiene pedidos asociados. Desactívalo primero.");
        }

        repository.delete(proveedor);
        return new DatosDetalleResponse(200, "Proveedor eliminado correctamente");
    }
}
