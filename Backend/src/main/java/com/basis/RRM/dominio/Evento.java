package com.basis.RRM.dominio;

import liquibase.pro.packaged.J;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evento")
@Getter
@Setter
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private LocalDate dataEvento;

    @Column(name = "justificativa_adiantamento")
    private String justificativa;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_motivo")
    private Motivo motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_situacao")
    private Situacao situacao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_evento", joinColumns = {
            @JoinColumn(name="evento")
    }, inverseJoinColumns = {
            @JoinColumn(name = "usuario")
    })
    private List<Usuario> usuario;



}
