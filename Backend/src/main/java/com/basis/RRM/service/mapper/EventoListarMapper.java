package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Evento;
import com.basis.RRM.service.dto.EventoListarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SituacaoSelectMapper.class})
public interface EventoListarMapper extends EntityMapper<EventoListarDTO, Evento> {
}
