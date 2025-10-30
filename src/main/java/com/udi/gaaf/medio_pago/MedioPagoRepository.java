package com.udi.gaaf.medio_pago;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio JPA para la entidad MedioPago.
 * Permite realizar operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {

    /**
     * Busca un medio de pago por su referencia.
     *
     * @param referencia Referencia única del medio de pago.
     * @return Un Optional con el medio de pago encontrado, o vacío si no existe.
     */
    Optional<MedioPago> findByReferencia(String referencia);

    /**
     * Busca un medio de pago asociado a un pedido por su ID.
     *
     * @param idPedido ID del pedido.
     * @return Un Optional con el medio de pago encontrado, o vacío si no existe.
     */
    Optional<MedioPago> findByPedidoId(Long idPedido);
}
