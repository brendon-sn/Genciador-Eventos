package com.basis.RRM.service.mapper;

import com.basis.RRM.dominio.Usuario;
import com.basis.RRM.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CargoSelectMapper.class})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {

}
