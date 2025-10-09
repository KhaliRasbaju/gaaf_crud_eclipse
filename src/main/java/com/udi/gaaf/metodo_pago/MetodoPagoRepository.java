package com.udi.gaaf.metodo_pago;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

	Optional<MetodoPago> findByNombre(String nombre);
}
