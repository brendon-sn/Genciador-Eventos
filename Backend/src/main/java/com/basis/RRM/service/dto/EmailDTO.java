package com.basis.RRM.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private String assunto;
    private String destinatario;
    private String corpo;
    private List<String> copias;
}
