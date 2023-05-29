package com.basis.RRM.repository;

import com.basis.RRM.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    List<Usuario> findByStatusTrue();
    List<Usuario> findByStatusFalse();

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
