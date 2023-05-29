package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Motivo;
import com.basis.RRM.service.dto.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface MotivoSelectmapper extends EntityMapper<SelectDTO, Motivo> {

    @Mapping(source = "id", target = "value")
    @Mapping(source = "motivo", target = "label")
    SelectDTO toDto(Motivo motivo);

    @InheritInverseConfiguration
    Motivo toEntity(SelectDTO selectDTO);
}
