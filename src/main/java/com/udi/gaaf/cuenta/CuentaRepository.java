package com.udi.gaaf.cuenta;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de operaciones CRUD sobre la entidad {@link Cuenta}.
 * <p>
 * Permite acceder y consultar cuentas bancarias asociadas a proveedores,
 * utilizando JPA y Spring Data.
 * </p>
 */
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    /**
     * Obtiene todas las cuentas asociadas a un proveedor por su NIT.
     *
     * @param nit identificador del proveedor
     * @return lista de cuentas asociadas al proveedor
     */
    List<Cuenta> findAllByProveedor_Nit(Long nit);

    /**
     * Busca una cuenta asociada a un proveedor específico por su NIT.
     *
     * @param nit identificador del proveedor
     * @return una cuenta opcional asociada al proveedor, o vacía si no existe
     */
    Optional<Cuenta> findByProveedorNit(Long nit);
}
