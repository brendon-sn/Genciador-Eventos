package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Cargo;
import com.basis.RRM.service.dto.SelectDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface CargoSelectMapper extends EntityMapper<SelectDTO, Cargo>{


    @Mapping(source = "id", target = "value")
    @Mapping(source = "cargo", target = "label")
    SelectDTO toDto(Cargo cargo);

    @InheritInverseConfiguration
    Cargo toEntity(SelectDTO selectDTO);
}
