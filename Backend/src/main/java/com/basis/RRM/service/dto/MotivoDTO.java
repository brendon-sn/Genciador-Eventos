package com.basis.RRM.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MotivoDTO {

    private Long id;
    @NotBlank
    private String motivo;
    @NotBlank
    private String descricao;


}
