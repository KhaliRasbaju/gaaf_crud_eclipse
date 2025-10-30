package com.udi.gaaf.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Pedido}.
 * Proporciona métodos para realizar operaciones CRUD sobre los pedidos.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
