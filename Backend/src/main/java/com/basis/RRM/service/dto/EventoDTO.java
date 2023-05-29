package com.basis.RRM.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EventoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull(message = "Data não pode ser nula\"")
    @Future(message = "Nao e possivel criar um evento em uma data passada")
    private LocalDate dataEvento;

    private String justificativa;

    @NotNull(message = "Valor não pode ser nulo")
    private Double valor;

    @NotNull(message = "Motivo não pode ser nulo")
    private SelectDTO motivo;
    @NotNull(message = "Situação não pode ser nula")
    private SelectDTO situacao;
    @NotNull(message = "Usuario não pode ser nulo")
    private List<SelectDTO> usuario;
}
