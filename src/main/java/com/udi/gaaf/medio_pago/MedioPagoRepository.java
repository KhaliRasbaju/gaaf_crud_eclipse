package com.udi.gaaf.medio_pago;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long>{
	Optional<MedioPago> findByReferencia(String referencia);
	
	Optional<MedioPago> findByPedidoId(Long idPedido);
	
}
