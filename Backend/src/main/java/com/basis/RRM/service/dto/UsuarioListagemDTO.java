package com.basis.RRM.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioListagemDTO {
    private Long id;
    private String nome;
    private SelectDTO cargo;
}
