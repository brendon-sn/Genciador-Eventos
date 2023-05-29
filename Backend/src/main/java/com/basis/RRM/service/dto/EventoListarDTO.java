package com.basis.RRM.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventoListarDTO {
    private Long id;
    private String nome;
    private LocalDate dataEvento;
}
