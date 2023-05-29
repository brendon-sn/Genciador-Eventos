package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.service.dto.UsuarioListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CargoSelectMapper.class})
public interface UsuarioListagemMapper extends EntityMapper<UsuarioListagemDTO, Usuario> {


}
