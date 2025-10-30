package com.udi.gaaf.departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para gestionar las operaciones CRUD de la entidad {@link Departamento}.
 * <p>
 * Proporciona métodos automáticos para persistencia, consulta y eliminación
 * gracias a la extensión de {@link JpaRepository}.
 * </p>
 * 
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
