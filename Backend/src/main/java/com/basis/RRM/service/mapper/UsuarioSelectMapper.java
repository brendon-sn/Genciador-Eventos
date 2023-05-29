package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.service.dto.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface UsuarioSelectMapper extends EntityMapper<SelectDTO, Usuario>{



    @Mapping(source = "id", target = "value")
    @Mapping(source = "nome", target = "label")
    SelectDTO toDto(Usuario Usuario);


    @InheritInverseConfiguration
    Usuario toEntity(SelectDTO selectDTO);

}
