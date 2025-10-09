package com.udi.gaaf.medio_pago;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udi.gaaf.metodo_pago.MetodoPago;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long>{
	Optional<MetodoPago> findByReferencia(String referencia);
}
