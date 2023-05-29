package com.basis.RRM.repository;

import com.basis.RRM.dominio.Evento;
import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.dominio.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>, JpaSpecificationExecutor<Evento> {


    Optional<List<Evento>> findByMotivo(Motivo motivo);
    boolean existsByDataEvento(LocalDate dataEvento);

    @Query("SELECT obj from Evento obj ORDER BY obj.dataEvento")
    List<Evento> findAllOrderDate();

    @Query("select e " +
            "from Evento e " +
            "where e.dataEvento = :data")
    Optional<Evento> findTodayEvento(@Param("data") LocalDate data);


    @Query("select e " +
            "from Evento e " +
            "where e.dataEvento >= :data "+
            "ORDER BY e.dataEvento" )
    List<Evento> findAllAfter(@Param("data") LocalDate data);

    List<Evento> getAllByUsuario(Usuario usuario);
}
