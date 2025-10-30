package com.udi.gaaf.cuenta;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.udi.gaaf.common.DatosDetalleResponse;
import com.udi.gaaf.entidad_bancaria.EntidadBancariaService;
import com.udi.gaaf.errors.NotFoundException;
import com.udi.gaaf.proveedor.Proveedor;

/**
 * Servicio que gestiona la lógica de negocio relacionada con las cuentas bancarias
 * de los proveedores dentro del sistema.
 * <p>
 * Permite crear, consultar, editar y eliminar cuentas, así como obtener detalles
 * asociados a las entidades bancarias y proveedores.
 * </p>
 *
 * <p>Depende de {@link CuentaRepository} para las operaciones CRUD y de
 * {@link EntidadBancariaService} para la validación de entidades bancarias.</p>
 *
 */
@Service
public class CuentaService {

    /** Repositorio para acceder a los datos de cuentas. */
    @Autowired
    private CuentaRepository repository;

    /** Servicio para la gestión de entidades bancarias asociadas. */
    @Autowired
    private EntidadBancariaService entidadBancariaService;

    /**
     * Convierte una entidad {@link Cuenta} en su modelo de detalle {@link DatosDetalleCuenta}.
     *
     * @param cuenta la cuenta a detallar
     * @param idEntidad identificador de la entidad bancaria asociada
     * @return un objeto {@link DatosDetalleCuenta} con la información formateada
     */
    private DatosDetalleCuenta detalleCuenta(Cuenta cuenta, Long idEntidad) {
        var detalleEntidad = entidadBancariaService.obtenerPorId(idEntidad);
        return new DatosDetalleCuenta(cuenta.getId(), cuenta.getNumero(), cuenta.getTipo(), detalleEntidad);
    }

    /**
     * Busca una cuenta bancaria por su identificador único.
     *
     * @param id identificador de la cuenta
     * @return la cuenta correspondiente
     * @throws NotFoundException si no existe una cuenta con el ID proporcionado
     */
    public Cuenta obtenerCuentaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada por el id: " + id));
    }

    /**
     * Crea una nueva cuenta bancaria para un proveedor.
     *
     * @param datos información de registro de la cuenta
     * @param proveedor proveedor asociado a la cuenta
     * @return los detalles de la cuenta recién creada
     */
    public DatosDetalleCuenta crear(DatosRegistrarCuenta datos, Proveedor proveedor) {
        var entidad = entidadBancariaService.obtenerEntidadBancariaPorId(datos.idEntidad());
        var cuenta = new Cuenta(datos, entidad, proveedor);
        var nuevaCuenta = repository.save(cuenta);
        return detalleCuenta(nuevaCuenta, nuevaCuenta.getEntidad().getId());
    }

    /**
     * Actualiza los datos de una cuenta existente asociada a un proveedor por su NIT.
     *
     * @param datos información actualizada de la cuenta
     * @param nit número de identificación tributaria del proveedor
     * @return respuesta detallada con el estado de la actualización
     * @throws NotFoundException si no existe una cuenta asociada al proveedor indicado
     */
    @Transactional
    public DatosDetalleResponse editar(DatosRegistrarCuenta datos, Long nit) {
        var entidad = datos.idEntidad() != null ? entidadBancariaService.obtenerEntidadBancariaPorId(datos.idEntidad()) : null;
        var cuenta = repository.findByProveedorNit(nit)
                .orElseThrow(() -> new NotFoundException("Cuenta no existe por el nit: " + nit));

        if (entidad != null) cuenta.setEntidad(entidad);
        if (cuenta.getNumero() != datos.numero()) cuenta.setNumero(datos.numero());
        if (cuenta.getTipo() != datos.tipo()) cuenta.setTipo(datos.tipo());

        repository.save(cuenta);
        return new DatosDetalleResponse(200, "Cuenta actualizada correctamente");
    }

    /**
     * Obtiene una cuenta por su ID y devuelve su representación detallada.
     *
     * @param id identificador único de la cuenta
     * @return detalles de la cuenta
     */
    public DatosDetalleCuenta obtenerPorId(Long id) {
        var cuenta = obtenerCuentaPorId(id);
        return detalleCuenta(cuenta, cuenta.getEntidad().getId());
    }

    /**
     * Obtiene una lista de todas las cuentas registradas en el sistema.
     *
     * @return lista de detalles de cuentas
     */
    public List<DatosDetalleCuenta> obtenerTodos() {
        var cuentas = repository.findAll();
        return cuentas.stream().map(c -> detalleCuenta(c, c.getEntidad().getId())).toList();
    }

    /**
     * Obtiene todas las cuentas asociadas a un proveedor específico.
     *
     * @param nit identificador del proveedor (NIT)
     * @return lista de cuentas del proveedor
     */
    public List<DatosDetalleCuenta> obtenerTodosPorNit(Long nit) {
        var cuentas = repository.findAllByProveedor_Nit(nit);
        return cuentas.stream().map(c -> detalleCuenta(c, c.getEntidad().getId())).toList();
    }

    /**
     * Elimina una cuenta bancaria por su identificador.
     *
     * @param id identificador único de la cuenta
     * @return respuesta con el estado de la operación
     */
    public DatosDetalleResponse eliminarPorId(Long id) {
        var cuenta = obtenerCuentaPorId(id);
        repository.delete(cuenta);
        return new DatosDetalleResponse(200, "Cuenta eliminada correctamente");
    }
}
