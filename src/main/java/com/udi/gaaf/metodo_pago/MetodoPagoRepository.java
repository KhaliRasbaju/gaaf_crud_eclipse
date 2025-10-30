package com.udi.gaaf.metodo_pago;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link MetodoPago}.
 * Permite realizar operaciones CRUD y búsquedas personalizadas.
 */
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    /**
     * Busca un método de pago por su nombre.
     *
     * @param nombre Nombre del método de pago.
     * @return Un Optional con el método de pago encontrado, o vacío si no existe.
     */
    Optional<MetodoPago> findByNombre(String nombre);
}