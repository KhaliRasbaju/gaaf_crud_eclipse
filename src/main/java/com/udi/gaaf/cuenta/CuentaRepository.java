package com.udi.gaaf.cuenta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	
	List<Cuenta> findAllByProveedor_Nit(Long nit);
	
	Optional<Cuenta> findByProveedorNit(Long nit);
	

}
