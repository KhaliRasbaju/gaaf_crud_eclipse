package com.udi.gaaf.ubicacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
	List<Ubicacion> findAllByProveedorByNit(Long id);
}
