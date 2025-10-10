package com.udi.gaaf.municipio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
	
	List<Municipio> findAllById_Ubicacion_Departamento(Long id);
}
