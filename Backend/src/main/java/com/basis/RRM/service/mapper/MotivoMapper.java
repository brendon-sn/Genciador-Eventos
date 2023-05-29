package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Motivo;
import com.basis.RRM.service.dto.MotivoDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface MotivoMapper extends EntityMapper<MotivoDTO, Motivo> {
}
