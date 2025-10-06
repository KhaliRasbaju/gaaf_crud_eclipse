package com.udi.gaaf.entidad_bancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadBancariaRepository extends JpaRepository<EntidadBancaria, Long> {
}
