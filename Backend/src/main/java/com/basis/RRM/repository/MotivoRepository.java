package com.basis.RRM.repository;

import com.basis.RRM.dominio.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {

}
